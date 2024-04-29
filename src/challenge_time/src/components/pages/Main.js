import React, {useEffect, useState} from 'react';
import styled from 'styled-components';

const Main = () => {

	const [challengeList, setChallengeList] = useState([]);

	useEffect(() => {
		async function fetchChallengeList() {
			try {
				const response = await fetch('http://localhost:8080/challenge/list', {
					method: 'GET',
					headers: {
						'Content-Type': 'application/json',
						'Authorization': 'Bearer ' + localStorage.getItem("accessToken"),
					},
				});
				const data = await response.json();
				setChallengeList(data);
			} catch (error) {
				console.error('Error fetching challenge list', error);
			}
		}

		fetchChallengeList();
	}, []);

	return (
		<Container>
			<Box>
				<h3>챌린지</h3>
				<StyledSelect id="select1">
					<option value="option1">옵션 1</option>
					<option value="option2">옵션 2</option>
					<option value="option3">옵션 3</option>
				</StyledSelect>
				<TableWrapper>
					<Table>
						<thead>
						<tr>
							<Th>이름</Th>
							<Th>챌린지 생성자</Th>
							<Th>참여 인원</Th>
						</tr>
						</thead>
						<tbody>
							{challengeList.map(challenge => (
								<tr key={challenge.id}>
									<Td>{challenge.name}</Td>
									<Td>{challenge.nickname}</Td>
									<Td>{challenge.memberNum}</Td>
								</tr>
							))}
						</tbody>
					</Table>
				</TableWrapper>
			</Box>
		</Container>
	);
};


const Container = styled.div`
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 100vh;
	padding: 20px;
	margin-top: 100px;
`;

const Box = styled.div`
	background-color: #f2f2f2;
	padding: 20px;
	margin-bottom: 50px;
	height: 500px;
	width: 100%;
`;

const TableWrapper = styled.div`
	overflow: auto; /* 표에서만 스크롤 적용 */
	height: 70%; /* 표 내용이 박스를 벗어나지 않도록 박스의 높이와 동일하게 설정 */
`;

const Table = styled.table`
	width: 100%;
	border-collapse: collapse;
	background-color: #ffffff;
	border-radius: 10px;
	border: none;
`;

const Th = styled.th`
	padding: 8px;
	text-align: center;
`;

const Td = styled.td`
	padding: 8px;
	text-align: center;
`;

const StyledSelect = styled.select`
    margin-bottom: 10px; /* 선택 상자와 표 사이의 간격 조정 */
    padding: 5px;
`;

export default Main;