package baguchan.japaricraftmod;

import baguchan.japaricraftmod.compat.*;
import baguchan.japaricraftmod.event.*;
import baguchan.japaricraftmod.gui.*;
import baguchan.japaricraftmod.handler.*;
import baguchan.japaricraftmod.world.*;
import baguchan.japaricraftmod.world.biome.*;
import baguchan.japaricraftmod.world.gen.*;
import net.minecraft.block.*;
import net.minecraft.creativetab.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.text.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.structure.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.network.*;
import net.minecraftforge.fml.common.registry.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraftforge.registries.*;
import org.apache.logging.log4j.*;


@Mod(modid = JapariCraftMod.MODID, name = JapariCraftMod.MODNAME, version = JapariCraftMod.VERSION, useMetadata = true, updateJSON = "https://raw.githubusercontent.com/pentantan/JapariCraftMod/master/src/main/japaricraftmod.json", dependencies = "required:forge@[14.23.5.2768,);after:twilightforest@[3.8.689,);")
public class JapariCraftMod {

    public static final String MODID = "japaricraftmod";
    public static final String VERSION = "5.2.7";
    public static final String MODNAME = "JapariCraftMod";


    //Modの情報を格納する。 mcmod.infoの上位互換
    @Mod.Metadata
    public static ModMetadata metadata;

    @SidedProxy(clientSide = "baguchan.japaricraftmod.client.ClientProxy", serverSide = "baguchan.japaricraftmod.ServerProxy")
    public static CommonProxy proxy;

    public static boolean twilightForestLoaded = false;

    @Mod.Instance(MODID)
    public static JapariCraftMod instance;
    public static final int ID_Tutorial_Gui = 0;
    public static final int ID_JAPARI_INVENTORY = 1;
    public static final CreativeTabs tabJapariCraft = new TabJapariCraft("JapariCraftTab");
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static DamageSource sandstarLow;

    @EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        JapariBlocks.registerBlocks(registry);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        JapariBlocks.registerItemBlocks(registry);
        JapariItems.registerItems(registry);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        JapariBlocks.registerModels();
        JapariItems.registerModels();
    }

    @SubscribeEvent
    public void registerEntityEntries(RegistryEvent.Register<EntityEntry> event) {
        JapariEntityRegistry.registerEntities();
    }

    @SubscribeEvent
    public void registerBiomes(RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> registry = event.getRegistry();

        JapariBiomes.registerBiomes(registry);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new SandStarOreGenerator(), 0);
        GameRegistry.registerWorldGenerator(new SandStarLabGenerator(), 0);
        if (event.getSide().isClient()) {
            JapariRenderingRegistry.registerRenderers();
        }
        MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
        MinecraftForge.EVENT_BUS.register(new LootTableEventHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new JapariGuiHandler());
        //メタ情報の登録
        loadMeta();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        twilightForestLoaded = Loader.isModLoaded("twilightforest");
        if (twilightForestLoaded) {
            JapariCompat.twilightCompat();

        } else {

            JapariCraftMod.LOGGER.warn(MODID + " is skipping! compatibility!");

        }

        JapariBiomes.registerBiomeTypes();
        JapariEntityRegistry.addSpawns();

        //MinecraftForge.EVENT_BUS.register(new StructureEventHandler());
        //SandStarLabPieces.registerSandStarLab();

        // チャンク生成時に追加構造物の生成が行われるようにフック
        VillagerRegistry villageRegistry = VillagerRegistry.instance();
        VillagerRegistry.instance().registerVillageCreationHandler(new ComponentJapariHouse1.VillageManager());
        MapGenStructureIO.registerStructureComponent(ComponentJapariHouse1.class, "JH1");

        //Villagerのレンダー
        ModVillagers.INSTANCE.init();

        sandstarLow = new DamageSource("sandstarlow") {
            @Override
            public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
                String s = "death.attack.sandstarlow";
                String s1 = s + ".player";
                return new TextComponentString(entityLivingBaseIn.getDisplayName().getFormattedText() + " ").appendSibling(new TextComponentTranslation(s1, entityLivingBaseIn.getDisplayName()));
            }
        }.setMagicDamage();
    }

    private void loadMeta() {
        metadata.modId = MODID;
        metadata.name = MODNAME;
        metadata.version = VERSION;
        metadata.description = ("けもフレ関連のアイテムを追加します");
        metadata.credits = ("");
        metadata.logoFile = ("assets/japaricraftmod/textures/logo.png");
        metadata.url = ("https://minecraft.curseforge.com/projects/japaricraftmod-2");

        metadata.autogenerated = false;
    }
}

