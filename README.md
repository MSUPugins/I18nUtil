# I18nUtil
The translation engine used by MSU plugins (or maybe your plugins!)

# How to use
- Copy [/src/main/java/vip/floatationdevice/msu/I18nUtil.java](https://github.com/MSUPugins/I18nUtil/blob/main/src/main/java/vip/floatationdevice/msu/I18nUtil.java) into your plugin source folder
(you need to change the `package` line if the destination package is not `vip.floatationdevice.msu` anymore)
- Create 'lang_xx_XX.yml' in your plugin resources folder ('xx_XX' can be adapted to the language you want to create)
- Create the key `language`, `language-file-version`, `language-file-contributor` in lang_xx_XX.yml
```yaml
# For example:
language: "English (US)"
language-file-version: 1
language-file-contributor: "MCUmbrella"
```
- Import I18nUtil in your code and add this line to your plugin startup code:
```java
I18nUtil.setLanguage(YourPlugin.class, "xx_XX");
// YourPlugin is the class name of your JavaPlugin - the one with onEnable() and onDisable()
// xx_XX just like before
```
- After then, the translation engine of your plugin is ready! You can translate any (existed) keys using this function:
```java
I18nUtil.translate("my-key");
// Just make sure you have "my-key" in your lang_xx_XX.yml file
```
- This repository is also a sample plugin that uses I18nUtil.
You can clone it and run `mvn package` to build it.
After that you can put it on your own server and see how it looks!
