import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";

import ApiTest from "./pages/ApiTest/ApiTest";
import AutobioViewer from "./pages/AutobioViewer/AutobioViewer";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<ApiTest />} />
        <Route path="/view" element={<AutobioViewer />} />
      </Routes>
    </Router>
  );
}

export default App;
