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

package elucent.albedo.lighting;

import java.util.concurrent.Callable;

import elucent.albedo.Albedo;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class LightProviderCapability  implements ICapabilitySerializable<NBTTagCompound> {

	public static final ResourceLocation IDENTIFIER = new ResourceLocation(Albedo.MODID, "Light_Data");
	
	private float r,g,b,a,radius;
	
	public LightProviderCapability(Light l){
		this.r = l.r;
		this.g = l.g;
		this.b = l.b;
		this.a = l.a;
		this.radius = l.radius;
	}
	
	public LightProviderCapability(float r, float g, float b, float a, float radius){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		this.radius = radius;
	}
	
    public static void register() {
        CapabilityManager.INSTANCE.register(LightProviderCapability.class, new LightProviderCapability.Storage(), new LightProviderCapability.Factory());
    }
    
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return Albedo.LIGHT_CAP != null && capability == Albedo.LIGHT_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return Albedo.LIGHT_CAP != null && capability == Albedo.LIGHT_CAP ? (T) this : null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setFloat("r", this.r);
        nbt.setFloat("g", this.g);
        nbt.setFloat("b", this.b);
        nbt.setFloat("a", this.a);
        nbt.setFloat("radius", this.radius);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		this.r = nbt.getFloat("r");
		this.g = nbt.getFloat("g");
		this.b = nbt.getFloat("b");
		this.a = nbt.getFloat("a");
		this.radius = nbt.getFloat("radius");
	}
	

    public static class Storage implements Capability.IStorage<LightProviderCapability> {

        @Override
        public NBTBase writeNBT(Capability<LightProviderCapability> capability, LightProviderCapability instance, EnumFacing side) {
            return null;
        }

        @Override
        public void readNBT(Capability<LightProviderCapability> capability, LightProviderCapability instance, EnumFacing side, NBTBase nbt) {

        }

    }

    public static class Factory implements Callable<LightProviderCapability> {
        @Override
        public LightProviderCapability call() throws Exception {
            return null;
        }
    }
    
    public float getR(){
    	return r;
    }
    public float getG(){
    	return g;
    }
    public float getB(){
    	return b;
    }
    public float getA(){
    	return a;
    }
    public float getRadius(){
    	return radius;
    }
    
	public void setLight(Light l){
		this.r = l.r;
		this.g = l.g;
		this.b = l.b;
		this.a = l.a;
		this.radius = l.radius;
	}

}
