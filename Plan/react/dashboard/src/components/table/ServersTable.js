import React from "react";
import {FontAwesomeIcon as Fa} from "@fortawesome/react-fontawesome";
import {
    faCaretSquareRight,
    faLineChart,
    faLink,
    faServer,
    faSortAlphaDown,
    faSortAlphaUp,
    faSortNumericDown,
    faSortNumericUp,
    faUser,
    faUsers
} from "@fortawesome/free-solid-svg-icons";
import {useTheme} from "../../hooks/themeHook";
import {useTranslation} from "react-i18next";
import Scrollable from "../Scrollable";
import {NavLink} from "react-router-dom";

const ServerRow = ({server, onQuickView}) => {
    const {t} = useTranslation();
    return (
        <tr>
            <td>{server.name}</td>
            <td className="p-1">
                <NavLink to={"/server/" + encodeURIComponent(server.serverUUID)}
                         title={t('html.label.serverAnalysis') + ': ' + server.name}
                         className={'btn bg-transparent col-light-green'}><Fa
                    icon={faLink}/> {t('html.label.serverAnalysis')}
                </NavLink>
            </td>
            <td>{server.players}</td>
            <td>{server.online}</td>
            <td className="p-1">
                <button className={'btn bg-light-blue float-right'}
                        title={t('html.label.quickView') + ': ' + server.name}
                        onClick={onQuickView}
                >
                    <Fa icon={faCaretSquareRight}/>
                </button>
            </td>
        </tr>
    );
}

const sortKeepOrder = () => 0;
const sortBySometimesNumericProperty = (propertyName) => (a, b) => {
    if (typeof (a[propertyName]) === 'number' && typeof (b[propertyName]) === 'number') return a[propertyName] - b[propertyName];
    if (typeof (a[propertyName]) === 'number') return 1;
    if (typeof (b[propertyName]) === 'number') return -1;
    return 0;
}
const sortByNumericProperty = (propertyName) => (a, b) => b[propertyName] - a[propertyName]; // Biggest first
const sortBeforeReverse = (servers, sortBy) => {
    return [...servers].sort(sortBy.sortFunction);
}

const reverse = (array) => {
    const reversedArray = [];
    for (let i = array.length - 1; i >= 0; i--) {
        reversedArray.push(array[i]);
    }
    return reversedArray;
}

const sort = (servers, sortBy, sortReversed) => {
    return sortReversed ? reverse(sortBeforeReverse(servers, sortBy)) : sortBeforeReverse(servers, sortBy);
}

const SortOptionIcon = {
    LETTERS: {
        iconAsc: faSortAlphaDown,
        iconDesc: faSortAlphaUp
    },
    NUMBERS: {
        iconAsc: faSortNumericUp,
        iconDesc: faSortNumericDown
    }
}

export const ServerSortOption = {
    ALPHABETICAL: {
        label: 'html.label.alphabetical',
        sortFunction: sortKeepOrder,
        ...SortOptionIcon.LETTERS
    },
    AVERAGE_TPS: {
        label: 'html.label.averageTps7days',
        sortFunction: sortBySometimesNumericProperty('avg_tps'),
        ...SortOptionIcon.NUMBERS
    },
    // DOWNTIME: 'html.label.downtime',
    LOW_TPS_SPIKES: {
        label: 'html.label.lowTpsSpikes7days',
        sortFunction: sortByNumericProperty('low_tps_spikes'),
        ...SortOptionIcon.NUMBERS
    },
    NEW_PLAYERS: {
        label: 'html.label.newPlayers7days',
        sortFunction: sortByNumericProperty('new_players'),
        ...SortOptionIcon.NUMBERS
    },
    PLAYERS_ONLINE: {
        label: 'html.label.playersOnlineNow',
        sortFunction: sortBySometimesNumericProperty('online'),
        ...SortOptionIcon.NUMBERS
    },
    REGISTERED_PLAYERS: {
        label: 'html.label.registeredPlayers',
        sortFunction: sortByNumericProperty('players'),
        ...SortOptionIcon.NUMBERS
    },
    UNIQUE_PLAYERS: {
        label: 'html.label.uniquePlayers7days',
        sortFunction: sortByNumericProperty('unique_players'),
        ...SortOptionIcon.NUMBERS
    },
}

const ServersTable = ({servers, onSelect, sortBy, sortReversed}) => {
    const {t} = useTranslation();
    const {nightModeEnabled} = useTheme();

    const sortedServers = sort(servers, sortBy, sortReversed);

    return (
        <Scrollable>
            <table className={"table mb-0 table-striped" + (nightModeEnabled ? " table-dark" : '')}>
                <thead>
                <tr>
                    <th><Fa icon={faServer}/> {t('html.label.server')}</th>
                    <th><Fa icon={faLineChart}/> {t('html.label.serverAnalysis')}</th>
                    <th><Fa icon={faUsers}/> {t('html.label.registeredPlayers')}</th>
                    <th><Fa icon={faUser}/> {t('html.label.playersOnline')}</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {sortedServers.length ? sortedServers.map((server, i) => <ServerRow key={i} server={server}
                                                                                    onQuickView={() => onSelect(servers.indexOf(server))}/>) :
                    <tr>
                        <td>{t('html.generic.none')}</td>
                        <td>-</td>
                        <td>-</td>
                        <td>-</td>
                    </tr>}
                </tbody>
            </table>
        </Scrollable>
    )
};

export default ServersTable;