import React from 'react';
import styled from 'styled-components';

const Challenge = () => {
    // 가상의 챌린지 데이터
    const challenge = {
        name: "Challenge 1",
        rules: [
            { title: "Rule 1", penalty: "$10", time: "1 hour", description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit." },
            { title: "Rule 2", penalty: "$15", time: "2 hours", description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit." },
            // 다른 규칙 추가
        ]
    };

    return (
        <Container>
            <ChallengeContainer>
                <h2>{challenge.name}</h2>
                <h3>참여 인원: </h3>
                <RulesList>
                    {challenge.rules.map((rule, ruleIndex) => (
                        <Rule key={ruleIndex}>
                            <RuleTitle>{rule.title}</RuleTitle>
                            <RuleDetails>
                                <p>Penalty: {rule.penalty}</p>
                                <p>Time: {rule.time}</p>
                                <p>Description: {rule.description}</p>
                            </RuleDetails>
                        </Rule>
                    ))}
                    <a>Your Total Penalty: </a>
                </RulesList>
            </ChallengeContainer>
        </Container>
    );
};

const Container = styled.div`
  margin-top: 20px;
  justify-content: center;
  align-items: center;
  height: 100vh;
`;

const ChallengeContainer = styled.div`
  background-color: #f9f9f9;
  padding: 20px;
  width: 100%;
`;

const RulesList = styled.div`
  margin-top: 10px;
`;

const Rule = styled.div`
  background-color: #ffffff;
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 10px;
  margin-bottom: 10px;
`;

const RuleTitle = styled.h3`
  margin-bottom: 5px;
`;

const RuleDetails = styled.div`
  p {
    margin: 5px 0;
  }
`;

export default Challenge;