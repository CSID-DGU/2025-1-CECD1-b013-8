import React, { useState } from "react";
import PDFViewer from "../../components/PDFViewer/PDFViewer";

function AutobioViewer() {
  const [pageNumber, setPageNumber] = useState(1);
  const [numPages, setNumPages] = useState(150);
  const [scale, setScale] = useState(1.0);
  const file = "/250220_방명국T_내지.pdf";
  return (
    <PDFViewer
      file={file}
      title="동막골 소년의 고백"
      pageNumber={pageNumber}
      numPages={numPages}
      onPageChange={(num) => setPageNumber(num)}
      onZoomIn={() => setScale((s) => Math.min(s + 0.1, 2))}
      onZoomOut={() => setScale((s) => Math.max(s - 0.1, 0.5))}
      onResetZoom={() => setScale(1.0)}
      onPreviousPage={() => setPageNumber((prev) => Math.max(prev - 1, 1))}
      onNextPage={() => setPageNumber((prev) => Math.min(prev + 1, numPages))}
      scale={scale}
    />
  );
}
export default AutobioViewer;
