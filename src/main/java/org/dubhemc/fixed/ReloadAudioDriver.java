package org.dubhemc.fixed;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.SystemToast;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;


@Mod("fixed")
@Mod.EventBusSubscriber
public class ReloadAudioDriver {

    public static final String MODID = "fixed";
    private static final Logger LOGGER = LogManager.getLogger(MODID);

    public ReloadAudioDriver() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (remote, isServer) -> true));
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onKeyInputEvent(InputEvent.KeyInputEvent event) {
        if (event.getAction() != GLFW.GLFW_PRESS)
            return;
        if (Minecraft.getInstance().currentScreen == null) {
            if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_F3)) {
                if (event.getKey() == GLFW.GLFW_KEY_R) {
                    Minecraft.getInstance().keyboardListener.actionKeyF3 = true;
                    LOGGER.info("Reloading sounds!");
                    Minecraft.getInstance().getSoundHandler().sndManager.reload();
                    LOGGER.info("Reloaded sounds!");
                    SystemToast.addOrUpdate(Minecraft.getInstance().getToastGui(), SystemToast.Type.TUTORIAL_HINT, new TranslationTextComponent("fixed.reload_audio_driver.toast.title"), new TranslationTextComponent("fixed.reload_audio_driver.toast.body"));
                } else if (event.getKey() == GLFW.GLFW_KEY_Q) {
                    Minecraft.getInstance().ingameGUI.getChatGUI().printChatMessage(new TranslationTextComponent("fixed.reload_audio_driver.details"));
                }
            }
        }
    }

}
