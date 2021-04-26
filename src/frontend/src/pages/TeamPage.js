import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { PieChart } from 'react-minimal-pie-chart';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchBreifCard } from '../components/MatchBreifCard';
import './TeamPage.scss';
import {Link} from 'react-router-dom';
export const TeamPage = () => {

    const { teamName } = useParams();
    const [team, setTeam] = useState({});

    

    

    useEffect(
        () =>{
            const fetchTeam = async () =>{
                const response = await fetch(`${process.env.REACT_APP_API_ROOT_URL}/team/${teamName}`);
                const data = await response.json();
                console.log(data)
                
                setTeam(data);
            };
            fetchTeam();
        },[teamName]
    
    );
   
if(!team || !team.teamName){
    return <h1>Error 404 !!! Team Not Found</h1>
}
  return (
    <div className="TeamPage">
      <div className = "team-name-section"><h1 className = "team-name">{team.teamName}</h1></div>
     <div className = "win-loss-section">Win/Losses
     <PieChart
  data={[
    { title: 'Losses', value: (team.toalMatchPlayed - team.totalWinMatch), color: '#a34d5d' },
    { title: 'Win', value: team.totalWinMatch, color: '#4da375' },
    
  ]}
/>
</div>
     <div className = "match-detail-section">
     <h3>Latest Matches</h3>
      <MatchDetailCard teamName = {team.teamName} match = {team.matches[0]}/>
      </div>
      
      {team.matches && team.matches.slice(1).map( match => < MatchBreifCard key={match.id} teamName = {team.teamName} match ={match} />)}
     <div className="more-link">
       <Link to ={`/team/${teamName}/year/${process.env.REACT_APP_DATAEND_YEAR}`}>More ></Link>
     </div>
    </div>
  );
}


