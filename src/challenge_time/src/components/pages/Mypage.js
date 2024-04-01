import React from 'react';
import styled from 'styled-components';

const Mypage = () => {
    return (
        <Container>
            <Profile>
                <ProfileImage src="profile.jpg" alt="Profile Image" />
                <ProfileInfo>
                    <h2>User Name</h2>
                    <p>Email: user@example.com</p>
                    <p>Location: City, Country</p>
                </ProfileInfo>
            </Profile>
            <Challenges>
                <h2>참여중인 챌린지</h2>
                <Challenge>
                    <ChallengeTitle>Challenge 1</ChallengeTitle>
                    <ChallengeDescription>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</ChallengeDescription>
                </Challenge>
                <Challenge>
                    <ChallengeTitle>Challenge 2</ChallengeTitle>
                    <ChallengeDescription>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</ChallengeDescription>
                </Challenge>
            </Challenges>
        </Container>
    );
};

const Container = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
`;

const Profile = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 20px;
`;

const ProfileImage = styled.img`
  margin-top: 10px;
    width: 150px;
    height: 150px;
    margin-bottom: 10px;
`;

const ProfileInfo = styled.div`
    text-align: center;
`;

const Challenges = styled.div`
    width: 80%;
`;

const Challenge = styled.div`
    background-color: #f9f9f9;
    border-radius: 5px;
    padding: 15px;
    margin-bottom: 10px;
`;

const ChallengeTitle = styled.h3`
    margin-bottom: 5px;
`;

const ChallengeDescription = styled.p`
    color: #666;
`;

export default Mypage;