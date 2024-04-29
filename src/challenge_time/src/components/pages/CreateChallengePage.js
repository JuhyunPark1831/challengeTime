import React, { useState } from 'react';
import styled from 'styled-components';

const CreateChallengePage = () => {
    const [challengeName, setChallengeName] = useState('');
    const [rules, setRules] = useState([{ title: '', penalty: '', days: [], times: [], description: '' }]);
    const [selectedHour, setSelectedHour] = useState('');
    const [selectedMinute, setSelectedMinute] = useState('');
    const [selectedTimes, setSelectedTimes] = useState([]);

    const handleInputChange = (index, event) => {
        const { name, value } = event.target;
        const list = [...rules];
        list[index][name] = value;
        setRules(list);
    };

    const handleAddRule = () => {
        setRules([...rules, { title: '', penalty: '', days: [], times: [], description: '' }]);
    };

    const handleRemoveRule = (index) => {
        const list = [...rules];
        list.splice(index, 1);
        setRules(list);
    };

    const handleHourChange = (event) => {
        setSelectedHour(event.target.value);
    };

    const handleMinuteChange = (event) => {
        setSelectedMinute(event.target.value);
    };

    const handleAddTime = () => {
        if (selectedHour && selectedMinute) {
            setSelectedTimes([...selectedTimes, `${selectedHour}:${selectedMinute}`]);
            setSelectedHour('');
            setSelectedMinute('');
        }
    };

    const handleRemoveTime = (time) => {
        setSelectedTimes(selectedTimes.filter(item => item !== time));
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(challengeName, rules, selectedTimes);
        // 여기서 새로운 챌린지를 생성하는 작업을 수행할 수 있습니다.
    };

    return (
        <Container>
            <CreateChallengeForm onSubmit={handleSubmit}>
                <h2>Create New Challenge</h2>
                <InputLabel>Challenge Name:</InputLabel>
                <Input
                    type="text"
                    value={challengeName}
                    onChange={(e) => setChallengeName(e.target.value)}
                    required
                />
                <h3>Rules:</h3>
                {rules.map((rule, index) => (
                    <RuleContainer key={index}>
                        <RemoveButton type="button" onClick={() => handleRemoveRule(index)}>-</RemoveButton>
                        <RuleInputs>
                            <InputLabel>Rule Title:</InputLabel>
                            <Input
                                type="text"
                                name="title"
                                value={rule.title}
                                onChange={(e) => handleInputChange(index, e)}
                                required
                            />
                            <InputLabel>Penalty:</InputLabel>
                            <Input
                                type="text"
                                name="penalty"
                                value={rule.penalty}
                                onChange={(e) => handleInputChange(index, e)}
                                required
                            />
                            <InputLabel>Days:</InputLabel>
                            <CheckboxGroup>
                                {['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Friday', 'Everyday'].map(day => (
                                    <CheckboxLabel key={day}>
                                        <Checkbox
                                            type="checkbox"
                                            name="days"
                                            value={day}
                                            checked={rule.days.includes(day)}
                                            onChange={(e) => handleInputChange(index, e)}
                                        />
                                        {day}
                                    </CheckboxLabel>
                                ))}
                            </CheckboxGroup>
                            <InputLabel>Times:</InputLabel>
                            <TimeSelection>
                                <HourSelect value={selectedHour} onChange={handleHourChange}>
                                    <option value="">Hour</option>
                                    {Array.from({ length: 24 }, (_, i) => (
                                        <option key={i} value={String(i).padStart(2, '0')}>{String(i).padStart(2, '0')}</option>
                                    ))}
                                </HourSelect>
                                <MinuteSelect value={selectedMinute} onChange={handleMinuteChange}>
                                    <option value="">Minute</option>
                                    {Array.from({ length: 60 }, (_, i) => (
                                        <option key={i} value={String(i).padStart(2, '0')}>{String(i).padStart(2, '0')}</option>
                                    ))}
                                </MinuteSelect>
                                <AddTimeButton type="button" onClick={handleAddTime}>Add Time</AddTimeButton>
                            </TimeSelection>
                            <SelectedTimes>
                                {selectedTimes.map((time, index) => (
                                    <SelectedTime key={index}>
                                        {time}
                                        <RemoveTimeButton type="button" onClick={() => handleRemoveTime(time)}>X</RemoveTimeButton>
                                    </SelectedTime>
                                ))}
                            </SelectedTimes>
                            <InputLabel>Description:</InputLabel>
                            <Input
                                type="text"
                                name="description"
                                value={rule.description}
                                onChange={(e) => handleInputChange(index, e)}
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
  width: 400px;
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
