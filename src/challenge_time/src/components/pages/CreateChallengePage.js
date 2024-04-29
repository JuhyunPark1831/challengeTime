import React, { useState } from 'react';
import styled from 'styled-components';

const CreateChallengePage = () => {
    const [challengeName, setChallengeName] = useState('');
    const [rules, setRules] = useState([{ title: '', penalty: '', days: '', times: '', description: '' }]);
    const [selectedDay, setSelectedDay] = useState('');
    const [selectedHour, setSelectedHour] = useState('');
    const [selectedMinute, setSelectedMinute] = useState('');

    const handleInputChange = (index, event) => {
        const { name, value } = event.target;
        const list = [...rules];
        list[index][name] = value;
        setRules(list);
    };

    const handleDayChange = (index, event) => {
        const { value } = event.target;
        setSelectedDay(value);
        handleInputChange(index, { target: { name: 'days', value } });
    };

// handleHourChange 함수 추가 부분
    const handleHourChange = (index, event) => {
        const { value } = event.target;
        setSelectedHour(value);
        handleInputChange(index, { target: { name: 'times', value: `${value}:${selectedMinute}` } });
    };

// handleMinuteChange 함수 추가 부분
    const handleMinuteChange = (index, event) => {
        const { value } = event.target;
        setSelectedMinute(value);
        handleInputChange(index, { target: { name: 'times', value: `${selectedHour}:${value}` } });
    };

    const handleAddRule = () => {
        setRules([...rules, { title: '', penalty: '', days: '', times: '', description: '' }]);
    };

    const handleRemoveRule = (index) => {
        const list = [...rules];
        list.splice(index, 1);
        setRules(list);
    };

    const handleSubmit = async (event) => {
        event.preventDefault()
        try {
            const response = await fetch('http://localhost:8080/challenge/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem("accessToken"),
                },
                body: JSON.stringify({
                    name: challengeName,
                    rules: rules.map(rule => ({
                        title: rule.title,
                        penalty: rule.penalty,
                        week: rule.days,
                        challengeTime: rule.times,
                        challengeComment: rule.description
                    }))
                })
            });

            if (!response.ok) {
                throw new Error('Failed to create challenge');
            }
        } catch (error) {
            console.error('Error creating challenge:', error);
        }
    };

    return (
        <Container>
            <CreateChallengeForm onSubmit={handleSubmit}>
                <h2>새로운 챌린지</h2>
                <InputLabel>챌린지 이름:</InputLabel>
                <Input
                    type="text"
                    value={challengeName}
                    onChange={(e) => setChallengeName(e.target.value)}
                    required
                />
                <h3>규칙:</h3>
                {rules.map((rule, ruleIndex) => (
                    <RuleContainer key={ruleIndex}>
                        <RemoveButton type="button" onClick={() => handleRemoveRule(ruleIndex)}>-</RemoveButton>
                        <RuleInputs>
                            <InputLabel>규칙 이름:</InputLabel>
                            <Input
                                type="text"
                                name="title"
                                value={rule.title}
                                onChange={(e) => handleInputChange(ruleIndex, e)}
                                required
                            />
                            <InputLabel>벌금:</InputLabel>
                            <Input
                                type="text"
                                name="penalty"
                                value={rule.penalty}
                                onChange={(e) => handleInputChange(ruleIndex, e)}
                                required
                            />
                            <InputLabel>요일:</InputLabel>
                            <CheckboxGroup>
                                {['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'].map(day => (
                                    <CheckboxLabel key={day}>
                                        <Checkbox
                                            type="checkbox"
                                            name="days"
                                            value={day}
                                            onChange={(e) => handleDayChange(ruleIndex, e)}
                                            checked={selectedDay === day}
                                        />
                                        {day}
                                    </CheckboxLabel>
                                ))}
                            </CheckboxGroup>
                            <InputLabel>시간:</InputLabel>
                            <TimeSelection>
                                <HourSelect
                                    value={selectedHour}
                                    onChange={(e) => handleHourChange(ruleIndex, e)}
                                    name="selectedHour"
                                >
                                    <option value="">Hour</option>
                                    {Array.from({ length: 24 }, (_, i) => (
                                        <option key={i} value={String(i).padStart(2, '0')}>{String(i).padStart(2, '0')}</option>
                                    ))}
                                </HourSelect>
                                <MinuteSelect
                                    value={selectedMinute}
                                    onChange={(e) => handleMinuteChange(ruleIndex, e)}
                                    name="selectedMinute"
                                >
                                    <option value="">Minute</option>
                                    {Array.from({ length: 60 }, (_, i) => (
                                        <option key={i} value={String(i).padStart(2, '0')}>{String(i).padStart(2, '0')}</option>
                                    ))}
                                </MinuteSelect>
                            </TimeSelection>
                            <InputLabel>상세 설명:</InputLabel>
                            <Input
                                type="text"
                                name="description"
                                value={rule.description}
                                onChange={(e) => handleInputChange(ruleIndex, e)}
                                required
                            />
                        </RuleInputs>
                    </RuleContainer>
                ))}
                <AddButton type="button" onClick={handleAddRule}>Add Rule</AddButton>
                <SubmitButton type="submit">Create Challenge</SubmitButton>
            </CreateChallengeForm>
        </Container>
    );
};

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin-top: 200px;
`;

const CreateChallengeForm = styled.form`
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 10px;
  width: 100%;
`;

const InputLabel = styled.label`
  margin-top: 10px;
  margin-bottom: 10px;
  display: block;
`;

const Input = styled.input`
  margin-bottom: 10px;
  width: 100%;
  padding: 8px;
  border-radius: 5px;
  border: 1px solid #ccc;
  width: 400px;
`;

const CheckboxGroup = styled.div`
  margin-bottom: 10px;
  width: 450px;
`;

const CheckboxLabel = styled.label`
  margin-right: 10px;
`;

const Checkbox = styled.input`
  margin-right: 5px;
`;

const RuleContainer = styled.div`
  margin-top: 20px;
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 5px;
`;

const RemoveButton = styled.button`
  background-color: #f44336;
  color: white;
  border: none;
  border-radius: 50%;
  cursor: pointer;
  margin-right: 10px;
  margin-top: 10px;
`;

const RuleInputs = styled.div`
  display: inline-block;
  width: 300px;
`;

const TimeSelection = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 10px;
`;

const HourSelect = styled.select`
  margin-right: 5px;
`;

const MinuteSelect = styled.select`
  margin-right: 5px;
`;

const AddTimeButton = styled.button`
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
`;

const SelectedTimes = styled.div`
  margin-top: 5px;
`;

const SelectedTime = styled.div`
  display: inline-block;
  margin-right: 5px;
`;

const RemoveTimeButton = styled.button`
  background-color: #f44336;
  color: white;
  border: none;
  border-radius: 50%;
  cursor: pointer;
  margin-left: 5px;
`;

const AddButton = styled.button`
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px;
  cursor: pointer;
  margin-top: 10px;
`;

const SubmitButton = styled.button`
  background-color: #008CBA;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px;
  cursor: pointer;
  margin-top: 20px;
`;

export default CreateChallengePage;
