package com.djrapitops.plan.system.locale;

import com.djrapitops.plan.api.exceptions.EnableException;
import com.djrapitops.plan.system.SubSystem;
import com.djrapitops.plan.system.file.FileSystem;
import com.djrapitops.plan.system.locale.lang.CmdHelpLang;
import com.djrapitops.plan.system.locale.lang.DeepHelpLang;
import com.djrapitops.plan.system.locale.lang.Lang;
import com.djrapitops.plan.system.locale.lang.PluginLang;
import com.djrapitops.plan.system.settings.Settings;
import com.djrapitops.plugin.api.utility.log.Log;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * System in charge of {@link Locale}.
 *
 * @author Rsl1122
 */
public class LocaleSystem implements SubSystem {

    private Locale locale;

    public static Map<String, Lang> getIdentifiers() {
        Lang[][] lang = new Lang[][]{
                CmdHelpLang.values(),
                DeepHelpLang.values(),
                PluginLang.values()
        };

        return Arrays.stream(lang)
                .flatMap(Arrays::stream)
                .collect(Collectors.toMap(Lang::getIdentifier, Function.identity()));
    }

    @Override
    public void enable() throws EnableException {
        File localeFile = FileSystem.getLocaleFile();

        if (Settings.WRITE_NEW_LOCALE.isTrue()) {
            writeNewDefaultLocale(localeFile);
        }

        if (localeFile.exists()) {
            loadFromFile(localeFile);
        } else {
            loadSettingLocale();
        }
    }

    private void writeNewDefaultLocale(File localeFile) {
        try {
            new LocaleFileWriter(Locale.forLangCode(LangCode.EN)).writeToFile(localeFile);
        } catch (IOException e) {
            Log.error("Failed to write new Locale file at " + localeFile.getAbsolutePath());
            Log.toLog(this.getClass().getName(), e);
        }
        Settings.WRITE_NEW_LOCALE.set(false);
        Settings.save();
    }

    private void loadSettingLocale() throws EnableException {
        try {
            locale = Locale.fromSetting();
        } catch (IOException e) {
            throw new EnableException("Failed to read locale from jar: " + Settings.LOCALE.toString(), e);
        }
    }

    private void loadFromFile(File localeFile) throws EnableException {
        try {
            locale = Locale.fromFile(localeFile);
        } catch (IOException e) {
            throw new EnableException("Failed to read locale file at " + localeFile.getAbsolutePath(), e);
        }
    }

    @Override
    public void disable() {
        // No action necessary on disable.
    }

    public Locale getLocale() {
        return locale;
    }
}