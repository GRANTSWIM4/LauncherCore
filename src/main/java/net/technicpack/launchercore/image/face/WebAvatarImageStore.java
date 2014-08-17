package net.technicpack.launchercore.image.face;

import net.technicpack.launchercore.image.IImageStore;
import net.technicpack.launchercore.mirror.MirrorStore;
import net.technicpack.platform.io.AuthorshipInfo;
import net.technicpack.utilslib.Utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class WebAvatarImageStore implements IImageStore<AuthorshipInfo> {
    private MirrorStore mirrorStore;

    public WebAvatarImageStore(MirrorStore mirrorStore) {
        this.mirrorStore = mirrorStore;
    }

    @Override
    public void downloadImage(AuthorshipInfo key, File target) {
        try {
            mirrorStore.downloadFile(key.getAvatar(), key.getUser(), target.getAbsolutePath());
        } catch (IOException e) {
            Utils.getLogger().log(Level.INFO, "Error downloading user avatar: " + key.getUser(), e);
        }
    }

    @Override
    public String getJobKey(AuthorshipInfo key) {
        return "user-avatar-"+key.getUser();
    }
}