package com.sarsk.warbears.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

// Extend the Horse entity instead of implementing our own copy of the features
// Copy the PolarBear features into here
public class IceBearEntity extends AbstractHorseEntity
{
    //TODO: Death by Ice Bear message

    private static final DataParameter<Boolean> IS_STANDING = EntityDataManager.createKey(IceBearEntity.class, DataSerializers.BOOLEAN);

    public float clientSideStandAnimation0;
    public float clientSideStandAnimation;

	public static final DataParameter<Integer> ICEBEAR_TYPE = EntityDataManager.<Integer>createKey(IceBearEntity.class, DataSerializers.VARINT);

    public IceBearEntity(EntityType<? extends IceBearEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public IceBearEntity(World worldIn)
    {
        this(DVEntityTypes.ICEBEAR, worldIn);
    }

    @Override
    protected void registerData()
    {
        super.registerData();
 
        this.dataManager.register(ICEBEAR_TYPE, Integer.valueOf(this.rand.nextInt(4)));
        this.dataManager.register(IS_STANDING, false);
    }
    
    @Override
    public void tick()
    {
    	super.tick();
    	//this.remove();

        // Bear standing animation
        if (this.world.isRemote) {
            if (this.clientSideStandAnimation != this.clientSideStandAnimation0) {
                this.recalculateSize();
            }

            this.clientSideStandAnimation0 = this.clientSideStandAnimation;
            if (this.isStanding()) {
                this.clientSideStandAnimation = MathHelper.clamp(this.clientSideStandAnimation + 1.0F, 0.0F, 6.0F);
            } else {
                this.clientSideStandAnimation = MathHelper.clamp(this.clientSideStandAnimation - 1.0F, 0.0F, 6.0F);
            }
        }

    }
    
    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
        compound.putInt("iceBearType", this.getUnicornType());
    }

    @Override
    public void read(CompoundNBT compound)
    {
        super.read(compound);
        this.setUnicornType(compound.getInt("iceBearType"));
    }

    protected void registerAttributes()
    {
        super.registerAttributes();
        // Horse stats
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
        this.getAttribute(JUMP_STRENGTH).setBaseValue(this.getModifiedJumpStrength());

        // Bear stats
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
    }

    protected SoundEvent getAmbientSound()
    {
        return this.isChild() ? SoundEvents.ENTITY_POLAR_BEAR_AMBIENT_BABY : SoundEvents.ENTITY_POLAR_BEAR_AMBIENT;
    }

    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_POLAR_BEAR_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_POLAR_BEAR_HURT;
    }
    
	protected SoundEvent getAngrySound()
	{//TODO: Angry polar bear?
		super.getAngrySound();
		return SoundEvents.ENTITY_HORSE_ANGRY;
	}
    public boolean isStanding() {
        return (Boolean)this.dataManager.get(IS_STANDING);
    }

    public void setStanding(boolean p_189794_1_) {
        this.dataManager.set(IS_STANDING, p_189794_1_);
    }

    @OnlyIn(Dist.CLIENT)
    public float getStandingAnimationScale(float p_189795_1_) {
        return MathHelper.lerp(p_189795_1_, this.clientSideStandAnimation0, this.clientSideStandAnimation) / 6.0F;
    }

    public boolean wearsArmor() {
        return true;
    }

    public boolean isArmor(ItemStack stack) {
        return stack.getItem() instanceof HorseArmorItem;
    }

    public boolean isBearFood(ItemStack stack) {
        if ((stack.getItem() == Items.SALMON) || (stack.getItem() == Items.COOKED_SALMON)) return true;
        if ((stack.getItem() == Items.COD) || (stack.getItem() == Items.COOKED_COD)) return true;

        return false;
    }

    public ItemStack getChestArmor() {
        return this.getItemStackFromSlot(EquipmentSlotType.CHEST);
    }

    protected float getWaterSlowDown() {
        return 0.98F;
    }

	public double getMountedYOffset()
	{
        //return super.getMountedYOffset() - 0.07D;
        return super.getMountedYOffset() - 0.05D;
	}

    public boolean processInteract(PlayerEntity player, Hand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        boolean flag = !itemstack.isEmpty();

        // TODO: Happy if given fish, or meat.  Angry if given puffer
        if (flag && isBearFood(itemstack))
        {
            return super.processInteract(player, hand);
        }
        else
        {
            if (!this.isChild())
            {
                if (this.isTame() && player.isSneaking())
                {
                    this.openGUI(player);
                    return true;
                }

                if (this.isBeingRidden())
                {
                    return super.processInteract(player, hand);
                }
            }

            if (flag)
            {
                if (this.handleEating(player, itemstack))
                {
                    if (!player.isCreative())
                    {
                        itemstack.shrink(1);
                    }

                    return true;
                }

                if (itemstack.interactWithEntity(player, this, hand))
                {
                    return true;
                }

                if (!this.isTame())
                {
                    this.makeMad();
                    return true;
                }

                //boolean flag1 = false; //HorseArmorItem.getByItemStack(itemstack) != HorseArmorType.NONE;
                boolean flag1 = !this.isChild() && isArmor(itemstack);
                boolean flag2 = !this.isChild() && !this.isHorseSaddled() && itemstack.getItem() == Items.SADDLE;

                if (flag1 || flag2)
                {
                    this.openGUI(player);
                    return true;
                }
            }

            if (this.isChild())
            {
                return super.processInteract(player, hand);
            }
            else
            {
                this.mountTo(player);
                return true;
            }
        }
    }
    
    public void setUnicornType(int type)
	{
		this.dataManager.set(ICEBEAR_TYPE, type);
	}

	public int getUnicornType()
	{
		return this.dataManager.get(ICEBEAR_TYPE);
	}
}