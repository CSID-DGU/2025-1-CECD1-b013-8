import React, { useState } from 'react';
import axios from 'axios';

const DataImporter = ({ jsonData }) => {
    const [progress, setProgress] = useState('');
    const [loading, setLoading] = useState(false);

    // 자서전 임시 데이터
    const autobio = {
        id: 1,
        title: "동막골 소년의 고백",
        visibility: "PUBLIC"
    };

    // ID 매핑 객체 생성
    const createMappings = async () => {
        const mappings = {
            person: {}, // 응답 시 부여되는 ID → DB 생성 ID
            place: {},  // 응답 시 부여되는 ID → DB 생성 ID
            event: {}   // 응답 시 부여되는 ID → DB 생성 ID
        };

        // 인물 등록
        for (const person of jsonData.people) {
            try {
                setProgress(`인물 등록 중: ${person.name}`);
                const response = await axios.post('/people', {
                    name: person.name,
                    description: person.description
                });
                mappings.person[person.id] = response.data;

                // 인물 별칭 등록
                for (const alias of person.alias) {
                    await axios.post('/person-aliases', {
                        person: response.data,
                        name: alias
                    });
                }
                console.log(`인물 등록 성공: ${person.name}`);
            } catch (error) {
                console.error(`인물 등록 실패: ${person.name}`, error);
            }
        }

        // 장소 등록
        for (const place of jsonData.places) {
            try {
                setProgress(`장소 등록 중: ${place.name}`);
                const response = await axios.post('/locations', {
                    name: place.name,
                    description: place.description
                });
                mappings.place[place.id] = response.data;

                // 장소 별칭 등록
                for (const alias of place.alias) {
                    await axios.post('/location-aliases', {
                        location: response.data,
                        name: alias
                    });
                }
                console.log(`장소 등록 성공: ${place.name}`);
            } catch (error) {
                console.error(`장소 등록 실패: ${place.name}`, error);
            }
        }

        return mappings;
    };

    // 사건 등록 및 연결
    const registerEvents = async (mappings) => {
        for (const event of jsonData.events) {
            try {
                setProgress(`사건 등록 중: ${event.name}`);
                console.log(progress);

                // 사건 등록
                const eventResponse = await axios.post('/events', {
                    name: event.name,
                    autobiography: autobio,
                    page: event.page
                });
                const newEvent = eventResponse.data;
                mappings.event[event.name] = newEvent; // 이름 기반 매핑

                // 시간 정보 등록 (null 값 제외)
                if (event.time.year || event.time.month || event.time.day) {
                    const timeResponse = await axios.post('/times', {
                        year: event.time.year,
                        month: event.time.month,
                        day: event.time.day
                    });

                    const newTime = timeResponse.data;
                    await axios.post('/event-time', {
                        event: newEvent,
                        time: newTime
                    });
                }

                // 사건-인물 연결
                for (const personId of event.people) {
                    const newPerson = mappings.person[personId];
                    if (newPerson) {
                        await axios.post('/event-person', {
                            event: newEvent,
                            person: newPerson
                        });
                    }
                }

                // 사건-장소 연결
                for (const placeId of event.places) {
                    const newPlace = mappings.place[placeId];
                    if (newPlace) {
                        await axios.post('/event-location', {
                            event: newEvent,
                            location: newPlace
                        });
                    }
                }

                console.log(`사건 등록 성공: ${event.name}`);
            } catch (error) {
                console.error(`사건 등록 실패: ${event.name}`, error);
            }
        }
    };

    // 전체 데이터 삽입 처리
    const handleImport = async () => {
        setLoading(true);
        setProgress('데이터 삽입 시작...');

        try {
            // 1. 인물 및 장소 등록
            const mappings = await createMappings();

            // 2. 사건, 시간 정보 및 연결 등록
            await registerEvents(mappings);

            setProgress('모든 데이터 삽입 완료!');
        } catch (error) {
            console.error('데이터 삽입 실패', error);
            setProgress('오류 발생: 콘솔 확인');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <button
                className="sendBtn"
                onClick={handleImport}
                disabled={loading}
            >
                {loading ? '처리 중...' : '데이터 삽입 시작'}
            </button>
            <div>{progress}</div>
        </div>
    );
};

export default DataImporter;