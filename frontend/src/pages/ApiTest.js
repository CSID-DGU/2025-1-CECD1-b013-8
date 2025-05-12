import React, { useState } from 'react';
import OpenAI from "openai";

import "./ApiTest.css";

const ApiTest = () => {
    const client = new OpenAI({
        apiKey: process.env.REACT_APP_OPENAI_API_KEY,
        dangerouslyAllowBrowser: true
    });

    const [systemPrompt, setSystemPrompt] = useState("");
    const [userMessage, setUserMessage] = useState("");
    const [result, setResult] = useState("");
    const [loading, setLoading] = useState(false);
    const [selectedFile, setSelectedFile] = useState("");
    const [selectedModel, setSelectedModel] = useState("gpt-4.1"); // 기본 모델

    // 요청 버튼
    const handleSend = async () => {
        if (!userMessage.trim()) return;

        setLoading(true);
        console.log("모델: " + selectedModel + "\n프롬프트: \n" + systemPrompt);

        const file = await client.files.create({
            file: selectedFile,
            purpose: "user_data",
        });

        try {
            const response = await client.responses.create({
                model: selectedModel,
                input: [
                    {
                        role: "developer",
                        content: systemPrompt
                    },
                    {
                        role: "user",
                        content: [
                            {
                                type: "input_file",
                                file_id: file.id,
                            },
                            {
                                type: "input_text",
                                text: userMessage,
                            },
                        ],
                    },
                ],
            });

            setResult(response.output_text);
        } catch (error) {
            console.error(error);
            setResult("에러 발생: " + error.message);
        } finally {
            setLoading(false);
        }
    };

    // 파일 업로드 시, 읽기
    const handleFileChange = (e) => {
        const file = e.target.files[0];

        if (!file) return;
        setSelectedFile(file);
    };

    return (
        <div className="test-body">
            <div className="chatContainer">
                <div className="chatBubble system">
                    {/* 모델 선택 셀렉트 박스 */}
                    <select
                        className="selectBox"
                        value={selectedModel}
                        onChange={(e) => setSelectedModel(e.target.value)}
                    >
                        <option value="gpt-4.1">gpt-4.1</option>
                        <option value="gpt-4.1-mini">gpt-4.1-mini</option>
                        <option value="gpt-4.1-nano">gpt-4.1-nano</option>
                        <option value="o4-mini">o4-mini</option>
                        <option value="o3-mini">o3-mini</option>
                    </select>
                    <div className="inputArea">
                        <textarea
                            className="system-textarea"
                            placeholder="프롬프트를 입력하세요..."
                            value={systemPrompt}
                            onChange={(e) => setSystemPrompt(e.target.value)}
                        />
                    </div>
                </div>

                {/* 파일 업로드 영역 */}
                <div className="fileUploadArea">
                    <label htmlFor="fileUpload" className="fileUploadLabel">
                        📎 파일 업로드
                    </label>
                    <input
                        id="fileUpload"
                        type="file"
                        accept=".pdf"
                        onChange={handleFileChange}
                        className="fileInput"
                    />
                    {selectedFile && <p className="fileName">선택됨: {selectedFile.name}</p>}
                </div>

                <div className="inputArea">
                    <textarea
                        className="user-textarea"
                        placeholder="메시지를 입력하세요..."
                        value={userMessage}
                        onChange={(e) => setUserMessage(e.target.value)}
                    />
                </div>

                <button className="sendBtn" onClick={handleSend} disabled={loading}>
                    {loading ? ". . ." : "➜"}
                </button>
            </div>

            <div className="resultArea">
                <p>결과</p>
                <textarea
                    className="result-textarea"
                    readOnly
                    value={result}
                />
            </div>
        </div>
    );
};

export default ApiTest;