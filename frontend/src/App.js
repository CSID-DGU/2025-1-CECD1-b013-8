import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";

import ApiTest from "./pages/ApiTest/ApiTest";
import SearchCard from "./components/common/SearchCard/SearchCard";
import AutobioViewer from "./pages/AutobioViewer/AutobioViewer";
import AutobioViewerLayout from "./pages/AutobioViewer/AutobioViewerLayout/AutobioViewerLayout";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<ApiTest />} />
        <Route path="/search" element={<SearchCard />} />
        <Route path="/view" element={<AutobioViewer />} />
        <Route path="/sidebar" element={<AutobioViewerLayout />} />
      </Routes>
    </Router>
  );
}

export default App;