import React from 'react';
import styled from 'styled-components';

const Main = () => {
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
						<tr>
							<Td>데이터 1</Td>
							<Td>데이터 2</Td>
							<Td>데이터 3</Td>
						</tr>
						<tr>
							<Td>데이터 1</Td>
							<Td>데이터 2</Td>
							<Td>데이터 3</Td>
						</tr>

						{/* 추가적인 행 및 열 데이터를 여기에 추가 */}
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