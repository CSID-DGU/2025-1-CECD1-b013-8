import React, { useState, useRef } from 'react';
import axios from 'axios';

import AutobioViewer from '../AutobioViewer';
import SearchCard from '../../../components/common/SearchCard/SearchCard';

import sidebarIcon from '../../../assets/images/sidebar_icon.svg'
import searchIcon from '../../../assets/images/search_icon.png'

import "./AutobioViewerLayout.css";

const AutobioViewerLayout = () => {
    const [sidebarOpen, setSidebarOpen] = useState(true);
    const [searchKeyword, setSearchKeyword] = useState(''); // 검색어 상태 추가
    const [searchedEvents, setSearchedEvents] = useState([]); // 검색 결과

    // 뷰어 lifting state up
    const [pageNumber, setPageNumber] = useState(1);
    const [numPages, setNumPages] = useState(150); // 또는 PDF 로드 후 업데이트
    const [scale, setScale] = useState(1.7);

    // 클릭으로 검색된 키워드 데이터
    const [clickedKeyword, setClickedKeyword] = useState(null);

    // 자서전 임시 데이터
    const autobio = {
        id: 1,
        title: "동막골 소년의 고백",
        visibility: "PUBLIC"
    };

    // 자식 컴포넌트의 ref 생성
    const pdfViewerRef = useRef();

    // // 다대다(곱연산)으로 넘어오는 DB 데이터 정돈 -> 백엔드로 코드 이전
    // const makeOrganizedEvents = (data) => {
    //     const grouped = {};

    //     data.forEach(row => {
    //         const eventId = row.event_id;

    //         // 이벤트 그룹 초기화
    //         if (!grouped[eventId]) {
    //             grouped[eventId] = {
    //                 id: eventId,
    //                 name: row.event_name,
    //                 persons: new Map(), // 중복 제거를 위해 Map 사용
    //                 locations: new Map()
    //             };
    //         }

    //         // 각 사건 관련 인물 유일하도록 매핑
    //         if (row.person_id && !grouped[eventId].persons.has(row.person_id)) {
    //             grouped[eventId].persons.set(row.person_id, {
    //                 id: row.person_id,
    //                 name: row.person_name,
    //                 description: row.person_description
    //             });
    //         }

    //         // 각 사건 관련 장소 유일하도록 매핑
    //         if (row.location_id && !grouped[eventId].locations.has(row.location_id)) {
    //             grouped[eventId].locations.set(row.location_id, {
    //                 id: row.location_id,
    //                 name: row.location_name,
    //                 description: row.location_description
    //             });
    //         }
    //     });

    //     // 매핑 데이터을 배열로 변환
    //     return Object.values(grouped).map(event => ({
    //         ...event,
    //         persons: Array.from(event.persons.values()),
    //         locations: Array.from(event.locations.values())
    //     }));
    // }

    const searchEventByKeyword = async (keyword) => {
        setClickedKeyword(null);

        const response = await axios.get(`/events/search?autobiographyId=${autobio.id}&keyword=${keyword}`);
        console.log(response.data);

        setSearchedEvents(response.data);
    };

    const toggleSidebar = () => {
        setSidebarOpen(!sidebarOpen);
    };

    const onClickSearchKeyword = (keyword) => {
        setSearchKeyword(keyword);
        searchEventByKeyword(keyword);
    };

    const handleSearch = () => {
        if (!searchKeyword.trim()) return;

        console.log('검색 키워드:', searchKeyword);

        searchEventByKeyword(searchKeyword);
    };

    const handleKeyPress = (e) => {
        if (e.key === 'Enter') {
            handleSearch();
        }
    };

    return (
        <div className="viewer-layout-container">
            {/* 사이드바 영역 */}
            <div className={`viewer-sidebar ${sidebarOpen ? 'open' : 'closed'}`}>
                <div className="sidebar-header">
                    <div className="header-title-container">
                        <button onClick={toggleSidebar} className="sidebar-toggle-btn">
                            <img
                                src={sidebarIcon}
                                alt="사이드바 토글"
                                className={`sidebar-icon ${sidebarOpen ? 'open' : ''}`}
                            />
                        </button>
                        {sidebarOpen &&
                            <h1 className="sidebar-header-title">자서전 검색</h1>
                        }
                    </div>

                    {sidebarOpen && (
                        <div className="sidebar-search-container">
                            <input
                                type="text"
                                placeholder="키워드를 입력하세요"
                                className="sidebar-search-input"
                                value={searchKeyword}
                                onChange={(e) => setSearchKeyword(e.target.value)}
                                onKeyPress={handleKeyPress}
                            />
                            <button className="sidebar-search-btn" onClick={handleSearch}>
                                <img src={searchIcon} alt="검색" className="search-icon" />
                            </button>
                        </div>
                    )}
                </div>

                {sidebarOpen && clickedKeyword && (
                    <div className="sidebar-description">
                        <div className="description-title">{clickedKeyword.title}</div>
                        <p className="description-text">{clickedKeyword.description}</p>
                    </div>
                )}

                {sidebarOpen && (
                    <>
                        {searchedEvents.length === 0 ? (
                            <div className="sidebar-content">
                                <img src={searchIcon} alt="검색" className="content-search-icon" />
                                <p className="search-guide">검색 키워드를 입력하세요</p>
                            </div>
                        ) : (
                            <div className="search-results-container">
                                <div className="search-results-title">검색 결과</div>
                                {searchedEvents.map((event) => (
                                    <SearchCard
                                        eventData={event}
                                        setPageNumber={setPageNumber}
                                        onClickSearchKeyword={onClickSearchKeyword}
                                        setClickedKeyword={setClickedKeyword}
                                    />
                                ))}
                            </div>
                        )}
                    </>
                )}
            </div>

            {/* 메인 콘텐츠 영역 */}
            <div className="viewer-main-area">
                <div className="viewer-main-content">
                    <AutobioViewer
                        pageNumber={pageNumber}
                        numPages={numPages}
                        scale={scale}
                        onPageChange={(num) => setPageNumber(num)}
                        onZoomIn={() => setScale((s) => Math.min(s + 0.1, 2))}
                        onZoomOut={() => setScale((s) => Math.max(s - 0.1, 0.5))}
                        onResetZoom={() => setScale(1.0)}
                        onPreviousPage={() => setPageNumber((prev) => Math.max(prev - 1, 1))}
                        onNextPage={() => setPageNumber((prev) => Math.min(prev + 1, numPages))}
                    />
                </div>
            </div>
        </div>
    );
};

export default AutobioViewerLayout;