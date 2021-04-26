import { React } from 'react';
import { Link } from 'react-router-dom';
import './MatchBreifCard.scss';
export const MatchBreifCard = ({teamName, match}) => {
    if(!match) return null;
    const otherTeam = match.team1 === teamName ? match.team2 : match.team1;
    const otherTeamRoute = `/team/${otherTeam}`;
    const isMatchWon = teamName === match.matchWinner;
    return (
    <div className={isMatchWon ? 'MatchBreifCard won-card' : 'MatchBreifCard lost-card'}>
      <span className = "vs">vs</span>
     <h3><Link to ={otherTeamRoute} > {otherTeam}</Link></h3>
  <p className="match-result">{match.matchWinner} won by {match.resultMargin} {match.result}</p>
     
    </div>
  );
}


