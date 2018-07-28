package com.djrapitops.plan.system.locale.lang;

/**
 * {@link Lang} implementation for in depth help language when /command ? is used.
 *
 * @author Rsl1122
 */
public enum DeepHelpLang implements Lang {
    PLAN("In Depth Help - /plan ?", "> §2Main Command\\  Access to subcommands and help\\  §2/plan §fList subcommands\\  §2/plan <subcommand> ? §fIn depth help"),
    ANALYZE("In Depth Help - /plan analyze ?", "> §2Analysis Command\\  Refreshes server page and displays link to the web page.\\\\  §2Aliases: §f/plan a|analyse|analysis"),
    SETUP("In Depth Help - /planbungee setup ?", "> §2Set-up toggle Command\\  Toggles set-up mode on Bungee.\\  Safeguard against unauthorized MySQL snooping with another server."),
    DISABLE("In Depth Help - /planbungee disable ?", "> §2Disable Command\\  Runs onDisable on PlanBungee.\\  Plugin can be enabled with /planbungee reload afterwards.\\  §bDoes not support swapping jar on the fly"),
    INSPECT("In Depth Help - /plan inspect ?", "> §2Inspect Command\\  Refreshes player page and displays link to the web page.\\  §2Alias: §f/plan <name>"),
    PLAYERS("In Depth Help - /plan players ?", "> §2Players Command\\  Displays link to the players page.\\  §2Aliases: §f/plan pl|list|playerlist"),
    SERVERS("In Depth Help - /plan servers ?", "> §2Servers Command\\  Displays list of Plan servers in the Database.\\  Can be used to debug issues with database registration on a network.\\  §2Aliases: §f/plan ls|sl|serverlist"),
    MANAGE("In Depth Help - /plan manage ?", "> §2Manage Command\\  Manage MySQL and SQLite database of Plan.\\  §2/plan m §fList subcommands\\  §2/plan m <subcommand> ? §fIn depth help\\  §2Alias: §f/plan m"),
    NETWORK("In Depth Help - /plan network ?", "> §2Network Command\\  Displays link to the network page.\\  If not on a network, this page displays the server page.\\  §2Alias: §f/plan n"),
    QINSPECT("In Depth Help - /plan qinspect ?", "> §2Quick Inspect Command\\  Displays some information about the player in game."),
    RELOAD("In Depth Help - /plan reload ?", "> §2Reload Command\\  Restarts the plugin using onDisable and onEnable.\\  §bDoes not support swapping jar on the fly"),
    SEARCH("In Depth Help - /plan search ?", "> §2Search Command\\  Get a list of Player names that match the given argument.\\§7  Example: /plan search 123 - Finds all users with 123 in their name."),
    UPDATE("In Depth Help - /plan update ?", "> §2Update Command\\  Used to update the plugin on the next shutdown\\  /plan update - Changelog link\\  /plan update -u - Schedule update to happen on all network servers that are online, next time they reboot.\\  /plan update cancel - Cancel scheduled update on servers that haven't rebooted yet."),
    WEB("In Depth Help - /plan web ?", "< §2Web User Manage Command.\\  §2/plan web §fList subcommands\\  §2/plan web <subcommand> ? §fIn Depth help\\  §2Alias: §f/plan web"),

    MANAGE_BACKUP("In Depth Help - /plan manage backup ?", "> §2Backup Subcommand\\  Creates a new SQLite database (.db file) with contents of currently active database in the Plan plugin folder."),
    MANAGE_CLEAR("In Depth Help - /plan manage clear ?", "> §2Clear Subcommand\\  Removes everything in the active database. Use with caution."),
    MANAGE_CON("In Depth Help - /plan manage con ?", "> §2Connection Debug Subcommand\\  Used to debug connections in the network.\\  Sends a request to each server in the database."),
    MANAGE_DISABLE("In Depth Help - /plan manage disable ?", "> §2Disable Subcommand\\  Can disable parts of the plugin until next reload.\\  Accepted arguments:\\    §2kickcount §fDisables kick counts in case /kickall is used on shutdown macro."),
    MANAGE_IMPORT("In Depth Help - /plan manage import ?", "> §2Import Subcommand\\  Import data from other sources.\\  Accepted Arguments:\\    §2offline §fBukkit player data, only register date and name."),
    MANAGE_MOVE("In Depth Help - /plan manage move ?", "> §2Move Subcommand\\  Move data from SQLite to MySQL or other way around.\\  Target database is cleared before transfer."),
    MANAGE_REMOVE("In Depth Help - /plan manage remove ?", "> §2Remove Subcommand\\  Remove player's data from the active database."),
    MANAGE_RESTORE("In Depth Help - /plan manage restore ?", "> §2Restore Subcommand\\  Restore a previous backup SQLite database (.db file)\\  You can also restore database.db from another server to MySQL.\\  Target database is cleared before transfer."),
    MANAGE_SETUP("In Depth Help - /plan manage setup ?", "> §2Setup Subcommand\\  Set-up a connection between Bungee and this server for network functionality.\\  BungeeAddress can be found in the enable log on console when Plan enables on Bungee."),

    WEB_REGISTER("In Depth Help - /plan web register ?", "> §2Register Subcommand\\  Registers a new Web User.\\  Registering a user for another player requires plan.webmanage permission.\\  Passwords are hashed with PBKDF2 (64,000 iterations of SHA1) using a cryptographically-random salt.\\  §2Alias: §f/plan register");

    private final String identifier;
    private final String defaultValue;

    DeepHelpLang(String identifier, String defaultValue) {
        this.identifier = identifier;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getDefault() {
        return defaultValue;
    }

}
