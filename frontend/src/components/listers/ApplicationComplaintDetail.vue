<template>
    <v-card outlined>
        <v-card-title>
            Complaint # {{item._links.self.href.split("/")[item._links.self.href.split("/").length - 1]}}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="신청번호" v-model="item.신청번호" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="서식일렬번호" v-model="item.서식일렬번호" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="서비스id" v-model="item.서비스id" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="서비스분류코드" v-model="item.서비스분류코드" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="신청인명" v-model="item.신청인명" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="주민등록번호" v-model="item.주민등록번호" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="신청처리결과코드" v-model="item.신청처리결과코드" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="신청일시" v-model="item.신청일시" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="등록일시" v-model="item.등록일시" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="수정일시" v-model="item.수정일시" :editMode="editMode" @change="change" />
            </div>
            <구비서류 offline label="구비서류" v-model="item.구비서류" :editMode="false" :key="false" @change="change" />
            <ReceiveMethod offline label="신청수령방법" v-model="item.신청수령방법" :editMode="false" :key="false" @change="change" />
            <ApplicationFile offline label="ApplicationFile" v-model="item.applicationFile" :editMode="false" :key="false" @change="change" />
        </v-card-text>

        <v-card-actions>
            <v-btn text color="deep-purple lighten-2" large @click="goList">List</v-btn>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="edit"
                    v-if="!editMode"
            >
                Edit
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="save"
                    v-else
            >
                Save
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="remove"
                    v-if="!editMode"
            >
                Delete
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="editMode = false"
                    v-if="editMode"
            >
                Cancel
            </v-btn>
        </v-card-actions>
    </v-card>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'ApplicationComplaintDetail',
        components:{},
        props: {
        },
        data: () => ({
            item: null,
            editMode: false,
        }),
        async created() {
            var me = this;
            var params = this.$route.params;
            var temp = await axios.get(axios.fixUrl('/complaints/' + params.id))
            if(temp.data) {
                me.item = temp.data
            }
        },
        methods: {
            goList() {
                var path = window.location.href.slice(window.location.href.indexOf("/#/"), window.location.href.lastIndexOf("/#"));
                path = path.replace("/#/", "/");
                this.$router.push(path);
            },
            edit() {
                this.editMode = true;
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.item._links.self.href))
                    }

                    this.editMode = false;

                    this.$emit('input', this.item);
                    this.$emit('delete', this.item);

                } catch(e) {
                    console.log(e)
                }
            },
        },
    };
</script>
