#ifndef LoRaWan_APP_H
#define LoRaWan_APP_H

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

enum eDeviceState_LoraWan
{
    DEVICE_STATE_INIT,
    DEVICE_STATE_JOIN,
    DEVICE_STATE_SEND,
    DEVICE_STATE_CYCLE,
    DEVICE_STATE_SLEEP
};

extern uint8_t devEui[];
extern uint8_t appEui[];
extern uint8_t appKey[];
extern uint8_t nwkSKey[];
extern uint8_t appSKey[];
extern uint32_t devAddr;
extern uint8_t appData[LORAWAN_APP_DATA_MAX_SIZE];
extern uint8_t appDataSize;
extern uint8_t appPort;
extern uint32_t txDutyCycleTime;
extern bool overTheAirActivation;
extern LoRaMacRegion_t loraWanRegion;
extern bool loraWanAdr;
extern bool isTxConfirmed;
extern uint32_t appTxDutyCycle;
extern DeviceClass_t loraWanClass;
extern bool passthroughMode;
extern uint8_t confirmedNbTrials;
extern bool modeLoraWan;
extern bool keepNet;
extern bool IsLoRaMacNetworkJoined;
extern uint16_t userChannelsMask[6];


/*!
 * Defines a random delay for application data transmission duty cycle. 1s,
 * value in [ms].
 */
#define APP_TX_DUTYCYCLE_RND                        1000

extern enum eDeviceState_LoraWan deviceState;

void LoraWanInit(DeviceClass_t lorawanClass,LoRaMacRegion_t region);
void LoraWanJoin();
void LoraWanSend();
void LoraWanCycle(uint32_t dutyCycle);
void LoraWanSleep();
void setDataRateForNoADR(int8_t dataRate);
void ifskipjoin();


uint16_t getBatteryVoltage(void);
bool SendFrame( void );
uint16_t getBatteryVoltage(void);
bool checkUserAt(char * cmd, char * content);
void downLinkDataHandle(McpsIndication_t *mcpsIndication);
void lwan_dev_params_update( void );
void dev_time_updated( void );


#endif
