#include <stdbool.h>
#include <string.h>
#include <stdint.h>
#include "stm32l1xx_hal.h"
#include <stdio.h>
#include "utilities.h"
#include "board.h"
#include "gpio.h"
#include "LoRaMac.h"
#include "Commissioning.h"
#include "Region.h"
#include "spi-board.h"
#include "rtc-board.h"
#include "sx1276.h"
#include "timer.h"

#include "main.h"

extern bool BewegungInterruptAusgeloest;
extern bool WurdeBewegt;
extern bool WurdeBestaetigt;

extern const uint32_t DutyCycle;

void BewegungssensorInit(void);
void PruefeInterruptStatus(void);
void SetzeBewegungssensorInDenSchlaf(void);
void HoleDenBewegungssensorAusDemSchlaf(void);
