#ifndef LoRa_APP_H
#define LoRa_APP_H

#include <stdio.h>
#include "utilities.h"
#include "board.h"
#include "gpio.h"
#include "LoRaMac.h"
#include "Commissioning.h"
#include "spi-board.h"
#include "rtc-board.h"
#include "sx1276.h"

enum eDeviceState_Lora
{
    LORA_INIT,
    LORA_SEND,
    LORA_RECEIVE,
    MCU_SLEEP,
};

extern enum eDeviceState_Lora deviceState_lora;
extern uint8_t Lora_TXPW;
extern uint32_t Lora_SF; 
extern uint32_t Lora_FREQ; 
extern uint32_t LoraRxTimeout;
extern bool PrintMode;
extern uint32_t Lora_BW;
extern uint32_t Lora_coderate; 
extern uint16_t lora_preamblelth;
extern bool lora_iqInvert;
extern bool lora_crc;
void LoraInit();
void LoraSend();
void LoraReceive();
void LoraLowpower();

#endif

