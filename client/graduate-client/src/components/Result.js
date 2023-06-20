import React from "react";
import {Table} from "react-bootstrap";

const Result = () => {
    const results = [
        {
            name: 'Илья',
            nameOfEvent: 'Автогонки',
            points: 5
        },
        {
            name: 'Вася',
            nameOfEvent: 'Мотогонки',
            points: 10
        },
    ];

    return (
        <Table striped bordered hover>
            <thead>
            <tr>
                <th>№</th>
                <th>Имя гонщика</th>
                <th>Название события</th>
                <th>Результат</th>
            </tr>
            </thead>
            <tbody>
            {results.map((result, index) => (
                <tr key={index}>
                    <td>{index + 1}</td>
                    <td>{result.name}</td>
                    <td><a href={'/competitions/' + index}>{result.nameOfEvent}</a></td>
                    <td>{result.points}</td>
                </tr>
            ))}
            </tbody>
        </Table>
    )
}

export default Result;