import React, { Component } from 'react';
import {BrowserRouter, Routes, Route, useLocation} from 'react-router-dom';
import Header from './components/common/Header';
import NavBar from "./components/common/NavBar";
import Index from './components/pages/index';
import Main from './components/pages/Main';
import Login from './components/pages/Login';
import Mypage from "./components/pages/Mypage";
import Challenge from "./components/pages/Challenge";
import CreateChallengePage from "./components/pages/CreateChallengePage";
import Product from './components/pages/Product';
import NotFound from './components/common/NotFound';

const pagesWithNavBar = ['/main', '/mypage', '/challenge', '/createChallenge'];

const App = () => {
	return (
		<BrowserRouter>
			<AppContent />
		</BrowserRouter>
	);
};

const AppContent = () => {
	const location = useLocation();
	const shouldRenderNavBar = pagesWithNavBar.some(page => location.pathname.startsWith(page));

	return (
		<div className='App'>
			<Header />
			{shouldRenderNavBar && <NavBar />}
			<Routes>
				<Route path="/" element={<Index />}></Route>
				<Route path="/login" element={<Login />}></Route>
				<Route path="/main" element={<Main />}></Route>
				<Route path="/mypage" element={<Mypage />}></Route>
				<Route path="/challenge" element={<Challenge />}></Route>
				<Route path="/createChallenge" element={<CreateChallengePage />}></Route>
				<Route path="/product/*" element={<Product />}></Route>
				<Route path="*" element={<NotFound />}></Route>
			</Routes>
		</div>
	);
};

export default App;