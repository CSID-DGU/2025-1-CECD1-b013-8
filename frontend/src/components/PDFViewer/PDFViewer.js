import React from "react";
import { Document, Page } from "react-pdf";
import Button from "../common/Button/Button";
import "./PDFViewer.css";
import Input from "../common/Input/Input";

function PDFViewer({
  file,
  title,
  pageNumber,
  numPages,
  onPageChange,
  onZoomIn,
  onZoomOut,
  onResetZoom,
  onPreviousPage,
  onNextPage,
  scale,
}) {
  return (
    <div className="pdf-viewer-container">
      <div className="pdf-viewer-header">
        <h2>{title}</h2>
        <div className="pdf-viewer-controls">
          <Button onClick={onZoomOut}>-</Button>
          <span>{Math.round(scale * 100)}%</span>
          <Button onClick={onZoomIn}>+</Button>
          <Button onClick={onResetZoom}>초기화</Button>
          <Button onClick={onPreviousPage}>&lt;</Button>
          <Input
            type="number"
            min={1}
            max={numPages}
            value={pageNumber}
            onChange={(e) => onPageChange(Number(e.target.value))}
            className="pdf-page-input"
          />
          <span>/ {numPages}</span>
          <Button onClick={onNextPage}>&gt;</Button>
        </div>
      </div>
      <div className="pdf-viewer-content">
        <Document
          file={file}
          onLoadSuccess={({ numPages }) => onPageChange(1, numPages)}
        >
          <Page
            pageNumber={pageNumber}
            scale={scale}
            renderTextLayer={false}
            renderAnnotationLayer={false}
          />
        </Document>
      </div>
    </div>
  );
}

export default PDFViewer;
