import React, { useState } from 'react';
import './SearchCard.css';

function SearchCard({ eventData, setPageNumber, onClickSearchKeyword, setClickedKeyword }) {
  const [isExpanded, setIsExpanded] = useState(false);

  console.log(eventData);

  // 오브젝트 리스트로 데이터 관리
  const sections = [
    {
      title: "인물",
      items: [...eventData.persons].sort((a, b) => a.id - b.id)
    },
    {
      title: "장소",
      items: [...eventData.locations].sort((a, b) => a.id - b.id)
    },
    {
      title: "시간",
      items: []
    }
  ];

  const toggleCard = () => {
    setIsExpanded(!isExpanded);
  };

  const handleSearchClickedKeyword = (section, item) => {
    const clickedItem = {
      title: section.title,
      ...item
    };

    onClickSearchKeyword(item.name);
    setClickedKeyword(clickedItem);
  };

  return (
    <div className="detail-card-container">
      <div className={`detail-card ${isExpanded ? 'expanded' : 'collapsed'}`}>
        <div className="detail-card-header">
          <div className="detail-card-title">{eventData.name}</div>
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
                {section.items.length > 0 && (
                  <div className="detail-section-title">{section.title}</div>
                )}
                <ul className="detail-item-list">
                  {section.items.map((item) => (
                    <li className="detail-item"
                      key={item.id}
                      onClick={() => handleSearchClickedKeyword(section, item)}>
                      {item.name}
                    </li>
                  ))}
                </ul>
              </div>
            ))}

            <div className="detail-move-section" onClick={() => setPageNumber(eventData.page)}>
              <div className="detail-arrow-icon">➔</div>
              <div className="detail-move-text">{eventData.page} 페이지로 이동</div>
            </div>
          </div>
        </div>
      </div>
    </div>

  );
}

export default SearchCard;