import React from "react";
import PDFViewer from "../../components/PDFViewer/PDFViewer";
import PdfFile from "../../assets/250220_방명국T_내지.pdf";

const AutobioViewer = ({
  pageNumber,
  numPages,
  scale,
  onPageChange,
  onZoomIn,
  onZoomOut,
  onResetZoom,
  onPreviousPage,
  onNextPage,
}) => {
  return (
    <PDFViewer
      file={PdfFile}
      title="동막골 소년의 고백"
      pageNumber={pageNumber}
      numPages={numPages}
      onPageChange={onPageChange}
      onZoomIn={onZoomIn}
      onZoomOut={onZoomOut}
      onResetZoom={onResetZoom}
      onPreviousPage={onPreviousPage}
      onNextPage={onNextPage}
      scale={scale}
    />
  );
};

export default AutobioViewer;