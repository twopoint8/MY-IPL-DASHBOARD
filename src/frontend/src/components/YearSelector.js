import {React} from 'react';
import {Link} from 'react-router-dom';
import './YearSelector.scss'
export const YearSelector = ({teamName}) => {

    let years =[];

    const startYear = process.env.REACT_APP_DATASTART_YEAR;
    const endYear = process.env.REACT_APP_DATAEND_YEAR;

    for(let i = startYear; i<=endYear; i++){
        years.push(i);
    }

    return (
        <ol className = "YearSelector">
        {years.map(year => (
        
        <li key={year}>
           <Link  to = {`/team/${teamName}/year/${year}`}>{year}</Link> 
        </li>
        
        ))}
        </ol>
    );
       
    
}
