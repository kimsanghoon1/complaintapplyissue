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

                    <ApplicationComplaint :offline="offline" class="video-card" :isNew="true" :editMode="true" v-model="newValue" @add="append" v-if="tick"/>
                
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
    import ApplicationComplaint from './../ApplicationComplaint.vue';

    export default {
        name: 'ApplicationComplaintManager',
        components: {
            ApplicationComplaint,
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
                    { text: "신청번호", value: "신청번호" },
                    { text: "서식일렬번호", value: "서식일렬번호" },
                    { text: "서비스id", value: "서비스id" },
                    { text: "서비스분류코드", value: "서비스분류코드" },
                    { text: "신청인명", value: "신청인명" },
                    { text: "주민등록번호", value: "주민등록번호" },
                    { text: "신청처리결과코드", value: "신청처리결과코드" },
                    { text: "신청일시", value: "신청일시" },
                    { text: "등록일시", value: "등록일시" },
                    { text: "수정일시", value: "수정일시" },
                    { text: "구비서류", value: "구비서류" },
                    { text: "신청수령방법", value: "신청수령방법" },
                    { text: "신청서파일", value: "신청서파일" },
                ],
            complaint : [],
            newValue: {},
            tick : true,
            openDialog : false,
        }),
        async created() {
            if(this.offline){
                if(!this.values) this.values = [];
                return;
            }

            var temp = await axios.get(axios.fixUrl('/complaints'))
            temp.data._embedded.complaints.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])
            this.values = temp.data._embedded.complaints;

            this.newValue = {
                '신청번호': 0,
                '서식일렬번호': '',
                '서비스id': '',
                '서비스분류코드': '',
                '신청인명': '',
                '주민등록번호': '',
                '신청처리결과코드': '',
                '신청일시': '2024-11-14',
                '등록일시': '2024-11-14',
                '수정일시': '2024-11-14',
                '구비서류': {},
                '신청수령방법': {},
                '신청서파일': {},
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

