import { React } from 'react';
import './TeamTiles.scss';
import {Link} from 'react-router-dom';
export const TeamTiles = ({teamName}) => {
    return(

        <div className="TeamTiles">
            <h1>
                <Link to = {`/team/${teamName}`}>
                    {teamName}
                </Link>
            </h1>
        </div>

    );
}