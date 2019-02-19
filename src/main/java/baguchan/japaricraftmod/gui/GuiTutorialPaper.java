package baguchan.japaricraftmod.gui;

import baguchan.japaricraftmod.handler.JapariItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class GuiTutorialPaper extends GuiScreen {

    //Tomさんのコードを参考にして、Entityは使わないのでなくした

    private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation("japaricraftmod:textures/gui/japaribook.png");

    private final EntityPlayer player;

    private final int bookImageWidth = 192;

    private final int bookImageHeight = 192;

    private int time = 0;

    private GuiTutorialPaper.NextPageButton buttonNextPage;

    private GuiTutorialPaper.NextPageButton buttonPreviousPage;

    private GuiButton buttonDone;

    private int currPage;

    private int bookTotalPages = 7;


    public GuiTutorialPaper(EntityPlayer player) {

        this.player = player;

        this.bookTotalPages = 7;

    }


    public void initGui() {

        this.buttonList.clear();

        this.buttonDone = this.addButton(new GuiButton(0, this.width / 2 - 100, 196, 200, 20, I18n.format("gui.done")));

        int i = (this.width - 192) / 2;

        int j = 2;

        this.buttonNextPage = this.addButton(new NextPageButton(1, i + 120, 156, true));

        this.buttonPreviousPage = this.addButton(new NextPageButton(2, i + 38, 156, false));

        this.updateButtons();

    }


    public void updateScreen() {

        super.updateScreen();

        this.time++;

    }


    private void updateButtons() {

        this.buttonNextPage.visible = this.currPage < this.bookTotalPages - 1;

        this.buttonPreviousPage.visible = this.currPage > 0;

        this.buttonDone.visible = true;

    }


    protected void actionPerformed(GuiButton button) throws IOException {

        if (button.enabled) {

            if (button.id == 0) {

                this.mc.displayGuiScreen(null);

            } else if (button.id == 1) {

                if (this.currPage < this.bookTotalPages - 1) {

                    ++this.currPage;

                }

            } else if (button.id == 2) {

                if (this.currPage > 0) {

                    --this.currPage;

                }

            }

            this.updateButtons();

        }

    }


    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        int i = (this.width - 192) / 2;

        this.mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);

        this.drawTexturedModalRect(i, 2, 0, 0, this.bookImageWidth, this.bookImageHeight);

        this.drawTexturedModalRect(i, 2, 0, 0, this.bookImageWidth, this.bookImageHeight);


        if (currPage == 2) {

            drawItemStack(new ItemStack(JapariItems.japariman), 70, 20, 2.2F);
            drawItemStack(new ItemStack(JapariItems.japarimancocoa), 90, 20, 2.2F);
            drawItemStack(new ItemStack(JapariItems.japarimanapple), 110, 20, 2.2F);
        }

        String s4 = I18n.format("book.pageIndicator", this.currPage + 1, this.bookTotalPages);

        String s5 = "???";

        String s6 = "???";


        if (this.currPage >= 0 && this.currPage < 7) {

            s5 = BookUtils.japariTranslate("page." + this.currPage + ".name");

            s6 = BookUtils.japariTranslate("page." + this.currPage + ".desc");

        }


        int j1 = this.fontRenderer.getStringWidth(s4);

        this.fontRenderer.drawString(s4, i - j1 + 192 - 44, 18, 0);

        this.fontRenderer.drawSplitString(s5, i + 60, 34, 116, 0);

        this.fontRenderer.drawSplitString(s6, i + 40, 80, 116, 0);

        super.drawScreen(mouseX, mouseY, partialTicks);

        int k = (this.width - this.bookImageWidth) / 2;

        byte b0 = 2;

    }


    private void drawItemStack(ItemStack stack, int x, int y, float scale) {

        GL11.glPushMatrix();

        GL11.glScalef(scale, scale, scale);

        GlStateManager.translate(0, 0, 32.0F);

        this.zLevel = 200.0F;

        this.itemRender.zLevel = 200.0F;

        net.minecraft.client.gui.FontRenderer font = null;

        if (!stack.isEmpty()) font = stack.getItem().getFontRenderer(stack);

        if (font == null) font = fontRenderer;

        this.itemRender.renderItemAndEffectIntoGUI(stack, x, y);

        this.itemRender.renderItemOverlayIntoGUI(font, stack, x, y, null);

        this.zLevel = 0.0F;

        this.itemRender.zLevel = 0.0F;

        GL11.glPopMatrix();

    }


    public void drawImage(ResourceLocation texture, int x, int y, int u, int v, int width, int height, float scale) {

        this.mc.renderEngine.bindTexture(texture);

        drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, scale, scale);

    }


    /**
     * Executes the click event specified by the given chat component
     */

    public boolean handleComponentClick(ITextComponent component) {

        ClickEvent clickevent = component.getStyle().getClickEvent();


        if (clickevent == null) {

            return false;

        } else if (clickevent.getAction() == ClickEvent.Action.CHANGE_PAGE) {

            String s = clickevent.getValue();


            try {

                int i = Integer.parseInt(s) - 1;


                if (i >= 0 && i < this.bookTotalPages && i != this.currPage) {

                    this.currPage = i;

                    this.updateButtons();

                    return true;

                }

            } catch (Throwable var5) {

                var5.printStackTrace();

            }


            return false;

        } else {

            boolean flag = super.handleComponentClick(component);


            if (flag && clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {

                this.mc.displayGuiScreen(null);

            }


            return flag;

        }

    }


    @SideOnly(Side.CLIENT)

    static class NextPageButton extends GuiButton {

        private final boolean isForward;


        public NextPageButton(int buttonId, int x, int y, boolean isForwardIn) {

            super(buttonId, x, y, 23, 13, "");

            this.isForward = isForwardIn;

        }


        /**
         * Draws this button to the screen.
         */

        public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {

            if (this.visible) {

                boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

                mc.getTextureManager().bindTexture(GuiTutorialPaper.BOOK_GUI_TEXTURES);

                int i = 0;

                int j = 192;


                if (flag) {

                    i += 23;

                }


                if (!this.isForward) {

                    j += 13;

                }


                this.drawTexturedModalRect(this.x, this.y, i, j, 23, 13);

            }

        }

    }

}