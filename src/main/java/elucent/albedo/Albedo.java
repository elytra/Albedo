/*
 * The MIT License
 *
 * Copyright (c) 2017 Elucent, William Thompson (unascribed), and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package elucent.albedo;

import elucent.albedo.lighting.LightProviderCapability;
import elucent.albedo.util.ShaderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Albedo.MODID, version=Albedo.VERSION, name=Albedo.NAME, guiFactory="elucent.albedo.gui.AlbedoGuiFactory", clientSideOnly=true)
public class Albedo {
	public static final String MODID = "albedo";
	public static final String VERSION = "2.0";
	public static final String NAME = "Albedo";
	
	@CapabilityInject(LightProviderCapability.class)
	public static final Capability<LightProviderCapability> LIGHT_CAP = null;
	
	public static boolean initialized = false;

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event) {
		((IReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener((irm) -> {
			ShaderUtil.reload(irm);
		});
		
		MinecraftForge.EVENT_BUS.register(new EventManager());
		MinecraftForge.EVENT_BUS.register(new ConfigManager());
		
		LightProviderCapability.register();
		
		ConfigManager.init(event.getSuggestedConfigurationFile());
	}
}
