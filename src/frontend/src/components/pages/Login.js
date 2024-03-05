import React from 'react';
import styled from 'styled-components';

const LoginForm = () => {
    return (
        <FormContainer>
            <FormHeader>Login</FormHeader>
            <Form>
                <InputLabel>Username</InputLabel>
                <InputField type="text" />
                <InputLabel>Password</InputLabel>
                <InputField type="password" />
                <LoginButton>Login</LoginButton>
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