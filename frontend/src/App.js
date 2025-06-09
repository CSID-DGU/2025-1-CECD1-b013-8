import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";

import ApiTest from "./pages/ApiTest/ApiTest";
import SearchCard from "./pages/SearchCard";

function App() {
  return (
    <Router>
        <Routes>
          <Route path="/" element={<ApiTest />} />
          <Route path="/side" element={<SearchCard />} />
        </Routes>
      </Router>
  );
}

export default App;
