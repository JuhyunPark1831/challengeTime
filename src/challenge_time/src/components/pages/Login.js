import React, {useState, ChangeEvent} from 'react';
import styled from 'styled-components';
import {useNavigate} from "react-router-dom";

const LoginForm = () => {
    const navigate = useNavigate();

    const [email, setemail] = useState("이메일");
    const [password, setPassword] = useState("비밀번호");

    const onChangeEmail = (event: ChangeEvent<HTMLInputElement>) => {
        setemail(event.target.value)
    }
    const onChangePassword = (event: ChangeEvent<HTMLInputElement>) => {
        setPassword(event.target.value)
    }

    const onClickLogin = async () => {
        navigate('/main')
        try {
            const response = await fetch('http://localhost:8080/user/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email, password })
            });

            if (!response.ok) {
                throw new Error('로그인에 실패했습니다.');
            }

            const data = await response.text();
            localStorage.setItem('accessToken', data);
            // navigate('/main'); // 로그인 성공 후 이동할 페이지 경로
        } catch (error) {
            console.error('로그인 에러:', error);
            // 에러 처리 로직 추가
        }
    }

    return (
        <FormContainer>
            <FormHeader>Login</FormHeader>
            <Form>
                <InputLabel>Email</InputLabel>
                <InputField type="text" onChange={onChangeEmail}/>
                <InputLabel>Password</InputLabel>
                <InputField type="password" onChange={onChangePassword}/>
                <LoginButton onClick={onClickLogin}>Login</LoginButton>
            </Form>
        </FormContainer>
    );
};

const FormContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 50px;
`;

const FormHeader = styled.h2`
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 20px;
`;

const Form = styled.form`
    display: flex;
    flex-direction: column;
    align-items: center;
`;

const InputLabel = styled.label`
    font-size: 16px;
    margin-bottom: 5px;
`;

const InputField = styled.input`
    width: 250px;
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
`;

const LoginButton = styled.button`
    width: 150px;
    padding: 10px;
    background-color: navy;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s;

    &:hover {
        background-color: #001f3f;
    }
`;

export default LoginForm;