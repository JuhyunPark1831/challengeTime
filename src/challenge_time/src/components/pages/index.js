import React from 'react';
import styled from 'styled-components';
import double_quotation from '../../assets/double_quotation.png';
import { useNavigate } from 'react-router-dom';

const Index = () => {
    const navigate = useNavigate();
    const handleStartClick = () => {
        navigate('/login');
    }
    return (
        <>
            <Container>
                <ContentContainer>
                    <QuotationImage src={double_quotation} alt="Quotation" />
                </ContentContainer>
                <ContentContainer>
                    <Text><BoldText>[Challenge Time]</BoldText>에 오신걸 환영합니다!</Text>
                    <Text>팀원들과 함께 Challenge를 인증하며 성공 확률을 높여봐요</Text>
                </ContentContainer>
                <ContentContainer>
                    <QuotationImage src={double_quotation} alt="Quotation" />
                </ContentContainer>
                <Button onClick={handleStartClick}>시작하기</Button>
            </Container>
        </>
    );
}

const Container = styled.div`
    display: flex;
    flex-direction: column; /* 요소들을 세로로 나란히 배치하기 위해 Flexbox 사용 */
    align-items: center; /* 가운데 정렬 */
`;

const Button = styled.button`
    background-color: navy;
    color: white;
    border: none;
    border-radius: 8px;
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s;
    margin-top: 50px; /* 요소들 사이의 간격 설정 */

    &:hover {
        background-color: #001f3f;
    }
`;

const ContentContainer = styled.div`
    display: flex;
    flex-direction: column; /* 요소들을 세로로 나란히 배치하기 위해 Flexbox 사용 */
    align-items: center; /* 세로 정렬 */
    margin-top: 30px; /* 요소들 사이의 간격 설정 */
`;

const QuotationImage = styled.img`
    width: 30px;
    height: auto;
`;

const Text = styled.span`
    margin-left: 5px; /* 텍스트와 이미지 사이의 간격 설정 */
    font-weight: 1;
`;

const BoldText = styled.span`
        font-weight: bold;
    `;

export default Index;