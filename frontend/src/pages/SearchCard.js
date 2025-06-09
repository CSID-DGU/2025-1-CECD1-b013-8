import React, { useState } from 'react';
import './SearchCard.css';

function SearchCard() {
  const [isExpanded, setIsExpanded] = useState(true);

  const title = "입대 집결"
  const page = 24

  // 오브젝트 리스트로 데이터 관리
  const sections = [
    {
      title: "인물",
      items: [
        { id: 1, content: "화자(재호)" },
        { id: 2, content: "입영자들" },
        { id: 3, content: "입영자들1" },
        { id: 4, content: "입영자들2" },
        { id: 3, content: "입영자들1123" },
        { id: 3, content: "입영자들1123123" },
        { id: 3, content: "입영자들112" },
        { id: 3, content: "입영자들1123" },
        { id: 3, content: "입영자들122" },
      ]
    },
    {
      title: "장소",
      items: [
        { id: 1, content: "만안국민학교" },
        { id: 2, content: "안양역" }
      ]
    },
    {
      title: "시간",
      items: [
        { id: 1, content: "1975-08-26" }
      ]
    }
  ];

  const toggleCard = () => {
    setIsExpanded(!isExpanded);
  };

  return (
    <div className="detail-card-container">
      <div className={`detail-card ${isExpanded ? 'expanded' : 'collapsed'}`}>
        <div className="detail-card-header">
          <div className="detail-card-title">{title}</div>
          <button
            className={`detail-toggle-button ${isExpanded ? 'up' : 'down'}`}
            onClick={toggleCard}
            aria-label={isExpanded ? "카드 접기" : "카드 펼치기"}
          >
            <span className="detail-arrow"></span>
          </button>
        </div>

        {/* Always render for animation, visibility controlled by CSS */}
        <div className={`detail-card-content-wrapper ${isExpanded ? 'expanded' : 'collapsed'}`}>
          <div className="detail-card-content">
            {sections.map((section) => (
              <div className="detail-section" key={section.title}>
                <div className="detail-section-title">{section.title}</div>
                <ul className="detail-item-list">
                  {section.items.map((item) => (
                    <li className="detail-item" key={item.id}>
                      {item.content}
                    </li>
                  ))}
                </ul>
              </div>
            ))}

            <div className="detail-move-section">
              <div className="detail-arrow-icon">➔</div>
              <div className="detail-move-text">{page} 페이지로 이동</div>
            </div>
          </div>
        </div>
      </div>
    </div>

  );
}

export default SearchCard;