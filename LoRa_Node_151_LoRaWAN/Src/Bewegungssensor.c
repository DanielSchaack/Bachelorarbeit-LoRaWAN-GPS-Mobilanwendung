#include "Bewegungssensor.h"


bool BewegungInterruptAusgeloest;
bool WurdeBewegt;


void BewegungssensorInit()
{
	BewegungInterruptAusgeloest = false;
	WurdeBewegt = true;
}

void PruefeInterruptStatus(void)
{
	if(BewegungInterruptAusgeloest==true)
		{
			BewegungInterruptAusgeloest=false;
			WurdeBewegt=true;
			SetzeBewegungssensorInDenSchlaf();
			RtcEnterLowPowerStopMode( );
		}
}

void PruefeConfirm(McpsConfirm_t *mcpsConfirm)
{
	if(mcpsConfirm->AckReceived)
	{
		SensorAktivieren();
		SetappTxDutyCycleInit(DutyCycle);
	}else{
		SetappTxDutyCycleInit(DutyCycle/2);
	}
}

void SensorAktivieren()
{
	WurdeBewegt = false;
	HoleDenBewegungssensorAusDemSchlaf();
}

void SetzeBewegungssensorInDenSchlaf(void)
{
	HAL_GPIO_WritePin(GPIOB, GPIO_PIN_14, GPIO_PIN_RESET);
}

void HoleDenBewegungssensorAusDemSchlaf(void)
{
	HAL_GPIO_WritePin(GPIOB, GPIO_PIN_14, GPIO_PIN_SET);
}


