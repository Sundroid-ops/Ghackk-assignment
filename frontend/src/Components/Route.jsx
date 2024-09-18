import React from 'react';
import { Routes, Route } from 'react-router-dom';

import Header from './Header';
import Footer from './Footer';
import HomePage from '../Pages/HomePage';
import NotFound from './NotFound';

export default function AppRoutes() {
  return (
    <div>
      <Header />
      <Routes>
        <Route path="/home" element={<HomePage />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
      <Footer />
    </div>
  );
}
