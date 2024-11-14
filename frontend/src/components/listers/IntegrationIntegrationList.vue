<template>
    <div>
    <h1>연계</h1>
        <v-row>
            <v-card
                class="mx-auto"
                style="height:300px; width:300px; margin-bottom:20px; text-align: center;"
                outlined
            >
                <v-list-item>
                    <v-list-item-avatar 
                        class="mx-auto"
                        size="80"
                        style="margin-top:80px;"
                    ><v-icon color="primary" x-large>mdi-plus</v-icon>
                    </v-list-item-avatar>
                </v-list-item>

                <v-card-actions>
                    <v-btn 
                        v-on="on"
                        class="mx-auto"
                        outlined
                        rounded
                        @click="openDialog=true;"
                        color="primary"
                        style="font-weight:500; font-size:20px; padding:15px; border:solid 2px; max-width:250px; overflow:hidden"
                    >
                        연계 등록
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-row>
        <v-list two-line>
            <template>
                <v-list-item v-for="(data, n) in values" :key="n">
                    <v-list-item-avatar color="grey darken-1">
                        <v-img :src="data.photo ? data.photo:'https://cdn.vuetifyjs.com/images/cards/cooking.png'"/>
                    </v-list-item-avatar>

                    <v-list-item-content>
                        <v-list-item-title style="margin-bottom:10px;">
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                        </v-list-item-title>

                        <v-list-item-subtitle style="font-size:25px; font-weight:700;">
                            [ 연계Id :  {{data.연계Id }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ 신청번호 :  {{data.신청번호 }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ 서비스Id :  {{data.서비스Id }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ 유형일렬번호 :  {{data.유형일렬번호 }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ 연계인터페이스 :  {{data.연계인터페이스 }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ 송신데이터 :  {{data.송신데이터 }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ 수신데이터 :  {{data.수신데이터 }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ 결과코드 :  {{data.결과코드 }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ 결과메시지 :  {{data.결과메시지 }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ 송신일시 :  {{data.송신일시 }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ 수신일시 :  {{data.수신일시 }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ 등록일시 :  {{data.등록일시 }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            [ 수정일시 :  {{data.수정일시 }} ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </v-list-item-subtitle>

                    </v-list-item-content>
                </v-list-item>

                <v-divider v-if="n !== 6" :key="`divider-${n}`" inset></v-divider>
            </template>
        </v-list>

        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                        v-model="openDialog"
                        width="332.5"
                        fullscreen
                        hide-overlay
                        transition="dialog-bottom-transition"
                >

                    <IntegrationIntegration :offline="offline" class="video-card" :isNew="true" :editMode="true" v-model="newValue" @add="append" v-if="tick"/>
                
                    <v-btn
                            style="postition:absolute; top:2%; right:2%"
                            @click="closeDialog()"
                            depressed 
                            icon 
                            absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>
            </div>
        </v-col>
    </div>
</template>

<script>
    const axios = require('axios').default;
    import IntegrationIntegration from './../IntegrationIntegration.vue';

    export default {
        name: 'IntegrationIntegrationManager',
        components: {
            IntegrationIntegration,
        },
        props: {
            offline: Boolean,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            values: [],
            newValue: {},
            tick : true,
            openDialog : false,
        }),
        async created() {
            if(this.offline){
                if(!this.values) this.values = [];
                return;
            } 

            var temp = await axios.get(axios.fixUrl('/integrations'))
            temp.data._embedded.integrations.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])
            this.values = temp.data._embedded.integrations;
            
            this.newValue = {
                '연계Id': 0,
                '신청번호': '',
                '서비스Id': '',
                '유형일렬번호': '',
                '연계인터페이스': '',
                '송신데이터': '',
                '수신데이터': '',
                '결과코드': '',
                '결과메시지': '',
                '송신일시': '2024-11-14',
                '수신일시': '2024-11-14',
                '등록일시': '2024-11-14',
                '수정일시': '2024-11-14',
            }
        },
        methods: {
            closeDialog(){
                this.openDialog = false
            },
            append(value){
                this.tick = false
                this.newValue = {}
                this.values.push(value)
                
                this.$emit('input', this.values);

                this.$nextTick(function(){
                    this.tick=true
                })
            }
        },
    };
</script>


<style>
    .video-card {
        width:300px; 
        margin-left:4.5%; 
        margin-top:50px; 
        margin-bottom:50px;
    }
</style>

