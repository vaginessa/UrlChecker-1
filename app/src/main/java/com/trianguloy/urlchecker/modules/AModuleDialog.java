package com.trianguloy.urlchecker.modules;

import android.app.Activity;

import com.trianguloy.urlchecker.dialogs.MainDialog;
import com.trianguloy.urlchecker.fragments.Fragment;
import com.trianguloy.urlchecker.url.UrlData;
import com.trianguloy.urlchecker.utilities.JavaUtils;

import java.util.Map;

/**
 * Base class for a module's dialog fragment.
 */
public abstract class AModuleDialog implements Fragment {

    // ------------------- private data -------------------

    private final MainDialog dialog;

    // ------------------- initialization -------------------

    public AModuleDialog(MainDialog dialog) {
        this.dialog = dialog;
    }

    // ------------------- abstract functions -------------------

    /**
     * Prepare a new url. This will always be called for each new module.
     */
    public void onPrepareUrl(UrlData urlData) {
    }

    /**
     * Analyze and optionally modify an url. This may not be called for new modules.
     * To modify the url call the setNewUrl callback. It will return true iff you can stop processing changes (false if you need to continue).
     * > if(setNewUrl.apply(new UrlData(""))) return;
     */
    public void onModifyUrl(UrlData urlData, JavaUtils.Function<UrlData, Boolean> setNewUrl) {
    }

    /**
     * Update UI and all needed for this final url. This will only be called for the final shown url.
     */
    public void onDisplayUrl(UrlData urlData) {
    }

    // ------------------- utilities -------------------

    /**
     * @return this activity context
     */
    protected final Activity getActivity() {
        return dialog;
    }

    /**
     * @return the current url
     */
    protected final String getUrl() {
        return dialog.getUrl();
    }

    /**
     * Changes the current url. (no extra data)
     *
     * @param url new url
     */
    protected final void setUrl(String url) {
        setUrl(new UrlData(url));
    }

    /**
     * Changes the current url.
     *
     * @param urlData new url and data
     */
    protected final void setUrl(UrlData urlData) {
        urlData.trigger = this;
        dialog.onNewUrl(urlData);
    }

    /**
     * saves global data
     */
    public void putData(String key, String value) {
        dialog.globalData.put(key, value);
    }

    /**
     * gets global data
     */
    public String getData(String key) {
        return dialog.globalData.get(key);
    }

    /**
     * returns the global data map, for advanced uses
     */
    public Map<String, String> getGlobalData() {
        return dialog.globalData;
    }

    /**
     * Changes this module visibility
     */
    protected final void setVisibility(boolean visible) {
        dialog.setModuleVisibility(this, visible);
    }

}
