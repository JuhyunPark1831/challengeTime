import React, { Component } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from './components/common/Header';
import Index from './components/pages/index';
import Main from './components/pages/Main';
import Login from './components/pages/Login';
import Product from './components/pages/Product';
import NotFound from './components/common/NotFound';

const App = () => {
	return (
		<div className='App'>
			<BrowserRouter>
				<Header />
				<Routes>
					<Route path="/" element={<Index />}></Route>
                    <Route path="/login" element={<Login />}></Route>
					<Route path="/product/*" element={<Product />}></Route>
					<Route path="*" element={<NotFound />}></Route>
				</Routes>
			</BrowserRouter>
		</div>
	);
};
export default App;