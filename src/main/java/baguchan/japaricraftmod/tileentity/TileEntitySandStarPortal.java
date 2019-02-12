package baguchan.japaricraftmod.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntitySandStarPortal extends TileEntity implements ITickable {

    public int tickCount;

    public void update() {
        ++this.tickCount;
    }
}
