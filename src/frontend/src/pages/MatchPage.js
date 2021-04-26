import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import './MatchPage.scss'
import { YearSelector } from '../components/YearSelector';

export const MatchPage = () => {

    const {teamName, year} = useParams();
  const[matches,setMatches] = useState([]);
    
  useEffect(

        () => {
            const fetchMatch = async () =>{
                const response  = await fetch(`http://192.168.1.15:9090/team/${teamName}/year/${year}`);
                const data =  await response.json();
                setMatches(data);
            };
            fetchMatch();
        },[teamName, year]

  );
  
  if(!matches){
    return <h1>Error 404 !!! Team Not Found</h1>
  }
  return (
    
    <div className="MatchPage">
      <div className="year-selector">
       <h3>Select Year</h3> 
        <YearSelector teamName = {teamName}/>
      </div>
      <div>         
<h1 className="page-heading">{teamName} matches in {year}</h1>
      {matches &&  matches.map( match => < MatchDetailCard key={match.id} teamName = {teamName} match ={match} />)}
      </div>

    </div>
  );
}

