import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../../assets/ChallengeTime_logo.png'

const Header = ({ includeNavBar }) => {
    const asdf = window.location.pathname;

    return (
        <div className="header-container">
            <div className="logo-container">
                <Link to="/" className="header-link">
                    <img src={logo} alt="Challenge Time Logo" style={{ width: '400px', height: 'auto', display: 'block', margin: '0 auto' }} />
                </Link>
                <a> {includeNavBar}</a>
            </div>
        </div>
    );
};


export default Header;