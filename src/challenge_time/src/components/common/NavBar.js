import {Link} from "react-router-dom";
import styled from "styled-components";
import React from "react";

const NavBar = () => {
    return (
        <NavbarContainer>
            <nav>
                <NavbarList>
                    <NavbarItem><NavbarLink to="/main">Main</NavbarLink></NavbarItem>
                    <NavbarItem><NavbarLink to="/createChallenge">new Challenge</NavbarLink></NavbarItem>
                    <NavbarItem><NavbarLink to="/mypage">MyPage</NavbarLink></NavbarItem>
                    <NavbarItem><NavbarLink to="/mypage">LogOut</NavbarLink></NavbarItem>
                </NavbarList>
            </nav>
        </NavbarContainer>
    );
};

const NavbarContainer = styled.div`
    background-color: #333;
`;

const NavbarList = styled.ul`
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
`;

const NavbarItem = styled.li`
    float: left;
  width: 25%;
`;

const NavbarLink = styled(Link)`
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;

    &:hover {
        background-color: #111;
    }
`;

export default NavBar;