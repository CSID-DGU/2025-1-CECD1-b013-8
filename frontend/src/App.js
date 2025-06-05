import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";

import ApiTest from "./pages/ApiTest/ApiTest";

function App() {
  return (
    <Router>
        <Routes>
          <Route path="/" element={<ApiTest />} />
        </Routes>
      </Router>
  );
}

export default App;
