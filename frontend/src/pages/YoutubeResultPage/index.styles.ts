import styled from '@emotion/styled';
import { MEDIA_QUERY, MOBILE_MAX_WIDTH } from '@/constants/media';
import { FONT_SIZE } from '@/constants/styles';
import { TypeWrapper } from '@/pages/SocialResultPage/index.styles';

export const Wrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  max-width: ${MOBILE_MAX_WIDTH}px;

  @media ${MEDIA_QUERY.DESKTOP} {
    width: auto;
    max-width: 100%;
  }
`;

export const TitleWrapper = styled(TypeWrapper)`
  display: none;
  @media ${MEDIA_QUERY.DESKTOP} {
    display: block;
  }
`;
export const YoutubeInfo = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 2rem;
  margin-top: 3rem;
  @media ${MEDIA_QUERY.DESKTOP} {
    flex-direction: row;
    justify-content: space-between;
  }
`;

export const VideoInfo = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 1rem;
  color: ${({ theme }) => theme.text};
`;

export const Title = styled.div`
  font-size: ${FONT_SIZE.H4};
  line-height: 1.8rem;
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-word;
  display: -webkit-box;
  -webkit-line-clamp: 2; // 원하는 라인수
  -webkit-box-orient: vertical;
`;
export const OwnerInfo = styled.div`
  display: flex;
  gap: 0.5rem;
  font-size: ${FONT_SIZE.BASE};
  margin-top: 1rem;
`;

export const OwnerName = styled.div``;

export const OwnerSubscribe = styled.div``;
