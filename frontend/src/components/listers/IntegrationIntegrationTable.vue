<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="values"
                :items-per-page="5"
                class="elevation-1"
        ></v-data-table>

        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                        v-model="openDialog"
                        width="332.5"
                        fullscreen
                        hide-overlay
                        transition="dialog-bottom-transition"
                >
                    <template v-slot:activator="{ on, attrs }">
                        <v-fab-transition>
                            <v-btn
                                    color="primary"
                                    fab
                                    dark
                                    large
                                    style="position:absolute; bottom: 5%; right: 2%; z-index:99"
                                    @click="openDialog=true;"
                            >
                                <v-icon v-bind="attrs" v-on="on">mdi-plus</v-icon>
                            </v-btn>
                        </v-fab-transition>
                    </template>

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
            headers: 
                [
                    { text: "연계Id", value: "연계Id" },
                    { text: "신청번호", value: "신청번호" },
                    { text: "서비스Id", value: "서비스Id" },
                    { text: "유형일렬번호", value: "유형일렬번호" },
                    { text: "연계인터페이스", value: "연계인터페이스" },
                    { text: "송신데이터", value: "송신데이터" },
                    { text: "수신데이터", value: "수신데이터" },
                    { text: "결과코드", value: "결과코드" },
                    { text: "결과메시지", value: "결과메시지" },
                    { text: "송신일시", value: "송신일시" },
                    { text: "수신일시", value: "수신일시" },
                    { text: "등록일시", value: "등록일시" },
                    { text: "수정일시", value: "수정일시" },
                ],
            integration : [],
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
            },
        }
    }
</script>

