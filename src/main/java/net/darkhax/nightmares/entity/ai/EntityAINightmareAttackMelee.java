package net.darkhax.nightmares.entity.ai;

import net.darkhax.bookshelf.util.EntityUtils;
import net.darkhax.nightmares.util.DamageSourceNightmare;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.util.EnumHand;

public class EntityAINightmareAttackMelee extends EntityAIAttackMelee {

	public EntityAINightmareAttackMelee(EntityCreature creature, double speedIn, boolean useLongMemory) {

		super(creature, speedIn, useLongMemory);
	}

	@Override
	protected void checkAndPerformAttack(EntityLivingBase target, double entityReach) {
		
		final double distance = this.getAttackReachSqr(target);

		if (entityReach <= distance && this.attackTick <= 0) {
			
			this.attackTick = 20;
			this.attacker.swingArm(EnumHand.MAIN_HAND);
			target.attackEntityFrom(new DamageSourceNightmare(this.attacker), (float) EntityUtils.getAttackDamage(this.attacker));
		}
	}
}
