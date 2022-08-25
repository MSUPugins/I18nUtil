package vip.floatationdevice.msu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static vip.floatationdevice.msu.I18nUtil.translate;

/**
 * The example plugin that uses I18nUtil.
 * @author MCUmbrella
 */
public class I18nUtilTestPlugin extends JavaPlugin implements CommandExecutor
{
    YamlConfiguration cfg;

    /**
     * Load config.yml and initialize translation engine.
     */
    void loadConfig()
    {
        File cfgFile = new File(getDataFolder(), "config.yml");
        if(!cfgFile.exists()) saveDefaultConfig(); // create default config.yml if not exist
        cfg = YamlConfiguration.loadConfiguration(cfgFile); // load it
        // set the language used by I18nUtil. if there's no 'language' key in config.yml, use "en_US"
        I18nUtil.setLanguage(I18nUtilTestPlugin.class, cfg.getString("language", "en_US"));
    }


    /**
     * Plugin startup codes.
     */
    @Override
    public void onEnable()
    {
        loadConfig();
        getCommand("i18ntest").setExecutor(this); // register /i18ntest command
        getLogger().info("Language: " + I18nUtil.getLanguage() + " (" + cfg.getString("language", "en_US") + ") by " + I18nUtil.getLanguageFileContributor());
        getLogger().info(translate("plugin-loaded")); // translate the 'plugin-loaded' key. the result depends on the value in lang_xx_XX.yml
    }


    /**
     * The /i18ntest command.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) // when someone used /i18ntest
    {
        if(args.length == 0) // /i18ntest
        {
            sender.sendMessage(translate("test-message")); // send the test message to the sender
            return true;
        }
        else if(args.length == 1 && args[0].equals("reload")) // /i18ntest reload
        {
            loadConfig(); // reload the config and I18nUtil
            sender.sendMessage("Plugin reloaded\nLanguage: " + I18nUtil.getLanguage() + " (" + cfg.getString("language", "en_US") + ") by " + I18nUtil.getLanguageFileContributor());
            return true;
        }
        else if(args.length == 2 && args[0].equals("translate")) // /i18ntest translate xxxxx
        {
            sender.sendMessage(translate(args[1])); // translate the given key and send the result to the sender
            return true;
        }
        else return false;
    }

    /**
     * Plugin shutdown codes.
     * Nothing to do.
     */
    @Override
    public void onDisable(){}
}
