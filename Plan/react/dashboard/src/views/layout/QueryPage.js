import React, {useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import {Outlet} from "react-router-dom";
import {useNavigation} from "../../hooks/navigationHook";
import {faUndo} from "@fortawesome/free-solid-svg-icons";
import {NightModeCss} from "../../hooks/themeHook";
import Sidebar from "../../components/navigation/Sidebar";
import Header from "../../components/navigation/Header";
import ColorSelectorModal from "../../components/modal/ColorSelectorModal";
import {useMetadata} from "../../hooks/metadataHook";
import ErrorPage from "./ErrorPage";

const QueryPage = () => {
    const {t, i18n} = useTranslation();
    const {isProxy, serverName} = useMetadata();

    const [error] = useState(undefined);
    const {sidebarItems, setSidebarItems} = useNavigation();

    const {currentTab, setCurrentTab} = useNavigation();

    useEffect(() => {
        const items = [
            {name: 'html.label.links'},
            {name: 'html.query.label.makeAnother', icon: faUndo, href: "/query"},
        ]

        setSidebarItems(items);
        window.document.title = `Plan | Query`;
        setCurrentTab('html.label.query')
    }, [t, i18n, setCurrentTab, setSidebarItems])

    const showBackButton = true;

    if (error) return <ErrorPage error={error}/>;

    const displayedServerName = !isProxy && serverName && serverName.startsWith('Server') ? "Plan" : serverName;
    return (
        <>
            <NightModeCss/>
            <Sidebar items={sidebarItems} showBackButton={showBackButton}/>
            <div className="d-flex flex-column" id="content-wrapper">
                <Header page={displayedServerName} tab={currentTab} hideUpdater/>
                <div id="content" style={{display: 'flex'}}>
                    <main className="container-fluid mt-4">
                        <Outlet context={{}}/>
                    </main>
                    <aside>
                        <ColorSelectorModal/>
                    </aside>
                </div>
            </div>
        </>
    )
}

export default QueryPage;